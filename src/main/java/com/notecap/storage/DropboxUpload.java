package com.notecap.storage;

/**
 * @author juanvladimir13 <juanvladimir13@gmail.com>
 * @see https://github.com/juanvladimir13
 */

import com.dropbox.core.DbxException;
import com.dropbox.core.NetworkIOException;
import com.dropbox.core.RetryException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.DbxPathV2;
import com.dropbox.core.v2.files.*;
import com.notecap.storage.dto.FileMetadataInfo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

public class DropboxUpload {

  private static final long CHUNKED_UPLOAD_CHUNK_SIZE = 8L << 20; // 8MiB
  private static final int CHUNKED_UPLOAD_MAX_ATTEMPTS = 5;

  private static FileMetadata uploadFile(DbxClientV2 dbxClient, File localFile, String dropboxPath) {
    try (InputStream in = new FileInputStream(localFile)) {
      return dbxClient.files().uploadBuilder(dropboxPath)
          .withMode(WriteMode.ADD)
          .withClientModified(new Date(localFile.lastModified()))
          .uploadAndFinish(in);
    } catch (UploadErrorException ex) {
      return null;
    } catch (DbxException ex) {
      return null;
    } catch (IOException ex) {
      return null;
    }
  }

  private static FileMetadata chunkedUploadFile(DbxClientV2 dbxClient, File localFile, String dropboxPath) {
    long size = localFile.length();

    if (size < CHUNKED_UPLOAD_CHUNK_SIZE) {
      return null;
    }

    long uploaded = 0L;
    DbxException thrown = null;

    String sessionId = null;
    for (int i = 0; i < CHUNKED_UPLOAD_MAX_ATTEMPTS; ++i) {
      if (i > 0) {
        System.out.printf("Retrying chunked upload (%d / %d attempts)\n", i + 1, CHUNKED_UPLOAD_MAX_ATTEMPTS);
      }

      try (InputStream in = new FileInputStream(localFile)) {
        in.skip(uploaded);

        // (1) Start
        if (sessionId == null) {
          sessionId = dbxClient.files().uploadSessionStart()
              .uploadAndFinish(in, CHUNKED_UPLOAD_CHUNK_SIZE)
              .getSessionId();
          uploaded += CHUNKED_UPLOAD_CHUNK_SIZE;
        }

        UploadSessionCursor cursor = new UploadSessionCursor(sessionId, uploaded);

        // (2) Append
        while ((size - uploaded) > CHUNKED_UPLOAD_CHUNK_SIZE) {
          dbxClient.files().uploadSessionAppendV2(cursor)
              .uploadAndFinish(in, CHUNKED_UPLOAD_CHUNK_SIZE);
          uploaded += CHUNKED_UPLOAD_CHUNK_SIZE;
          cursor = new UploadSessionCursor(sessionId, uploaded);
        }

        // (3) Finish
        long remaining = size - uploaded;
        CommitInfo commitInfo = CommitInfo.newBuilder(dropboxPath)
            .withMode(WriteMode.ADD)
            .withClientModified(new Date(localFile.lastModified()))
            .build();
        FileMetadata metadata = dbxClient.files().uploadSessionFinish(cursor, commitInfo)
            .uploadAndFinish(in, remaining);
        return metadata;
      } catch (RetryException ex) {
        thrown = ex;
        // RetryExceptions are never automatically retried by the client for uploads. Must
        // catch this exception even if DbxRequestConfig.getMaxRetries() > 0.
        sleepQuietly(ex.getBackoffMillis());
        continue;
      } catch (NetworkIOException ex) {
        thrown = ex;
        // network issue with Dropbox (maybe a timeout?) try again
        continue;
        //} catch (UploadSessionLookupErrorException ex) {
      } catch (UploadSessionAppendErrorException ex) {
        if (ex.errorValue.isIncorrectOffset()) {
          thrown = ex;
          // server offset into the stream doesn't match our offset (uploaded). Seek to
          // the expected offset according to the server and try again.
          uploaded = ex.errorValue
              .getIncorrectOffsetValue()
              .getCorrectOffset();
          continue;
        } else {
          return null;
        }
      } catch (UploadSessionFinishErrorException ex) {
        if (ex.errorValue.isLookupFailed() && ex.errorValue.getLookupFailedValue().isIncorrectOffset()) {
          thrown = ex;
          // server offset into the stream doesn't match our offset (uploaded). Seek to
          // the expected offset according to the server and try again.
          uploaded = ex.errorValue
              .getLookupFailedValue()
              .getIncorrectOffsetValue()
              .getCorrectOffset();
          continue;
        } else {
          return null;
        }
      } catch (DbxException ex) {
        return null;
      } catch (IOException ex) {
        return null;
      }
    }
    return null;
  }

  private static void sleepQuietly(long millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException ex) {
      // just exit
      System.err.println("Error uploading to Dropbox: interrupted during backoff.");
      System.exit(1);
    }
  }

  public static FileMetadataInfo uploadFileChunkedToCloudStorage(String localPath, String dropboxFolder, String dropboxFilename) {
    String dropboxPath = dropboxFolder + "/" + dropboxFilename;

    String pathError = DbxPathV2.findError(dropboxPath);
    if (pathError != null) {
      return null;
    }

    File localFile = new File(localPath);
    if (!localFile.exists()) {
      return null;
    }

    if (!localFile.isFile()) {
      return null;
    }

    DbxClientV2 dbxClient = DropboxClient.getInstance().getDbxClient();
    if (dbxClient == null) {
      return null;
    }

    FileMetadata metadata = null;
    if (localFile.length() <= (2 * CHUNKED_UPLOAD_CHUNK_SIZE)) {
      metadata = uploadFile(dbxClient, localFile, dropboxPath);
    } else {
      metadata = chunkedUploadFile(dbxClient, localFile, dropboxPath);
    }

    if (metadata == null) return null;

    return new FileMetadataInfo(
        metadata.getId(),
        metadata.getName(),
        metadata.getRev(),
        metadata.getPathLower(),
        metadata.getPathDisplay()
    );
  }

  public static FileMetadata uploadFileToCloudStorage(String localFile, String dropboxFolder, String dropboxFilename) {
    String dropboxPath = dropboxFolder + "/" + dropboxFilename;

    String pathError = DbxPathV2.findError(dropboxPath);
    if (pathError != null) {
      return null;
    }

    DbxClientV2 dbxClient = DropboxClient.getInstance().getDbxClient();
    if (dbxClient == null) {
      return null;
    }

    InputStream in = null;

    try {
      in = new FileInputStream(localFile);
    } catch (FileNotFoundException ex) {
      return null;
    }

    try {
      return dbxClient.files().uploadBuilder(dropboxPath)
          .uploadAndFinish(in);
    } catch (Exception e) {
      return null;
    }
  }
}
