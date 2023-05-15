package com.notecap.storage;

/**
 * @author juanvladimir13 <juanvladimir13@gmail.com>
 * @see https://github.com/juanvladimir13
 */

import com.dropbox.core.DbxAuthInfo;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.DbxPathV2;
import com.dropbox.core.v2.files.DeleteResult;
import com.dropbox.core.v2.files.FolderMetadata;
import com.dropbox.core.v2.users.FullAccount;
import com.notecap.storage.dto.FolderInfo;

public class DropboxClient {
  private static DropboxClient dropboxClient;
  private DbxClientV2 dbxClient;
  private DbxRequestConfig requestConfig;

  private DropboxClient() {
    requestConfig = new DbxRequestConfig("@juanvladimir13");
    DbxAuthInfo authInfo = DropboxConfig.readAuthInfoFile();

    if (authInfo != null) {
      dbxClient = new DbxClientV2(requestConfig, authInfo.getAccessToken(), authInfo.getHost());
      System.out.println("Autorizado");
    } else {
      dbxClient = null;
      System.out.println("No Autorizado");
    }
  }

  public static DropboxClient getInstance() {
    if (dropboxClient == null) {
      dropboxClient = new DropboxClient();
    }
    return dropboxClient;
  }

  public DbxClientV2 getDbxClient() {
    return dbxClient;
  }

  public DbxRequestConfig getRequestConfig() {
    return requestConfig;
  }

  public FolderInfo createFolderInDropboxPersonalAccount(String dropboxPath, String dropboxFolder) {
    String folder = dropboxPath + "/" + dropboxFolder;
    String pathError = DbxPathV2.findError(folder);
    if (pathError != null) {
      System.out.println(pathError);
      return null;
    }

    try {
      FolderMetadata folderMetadata = dbxClient.files().createFolderV2(folder, false).getMetadata();
      return new FolderInfo(
          folderMetadata.getId(),
          folderMetadata.getName(),
          folderMetadata.getPathLower(),
          folderMetadata.getPathDisplay()
      );

    } catch (DbxException e) {
      System.out.println(e.getMessage());
      return null;
    }
  }

  public DeleteResult deleteFolderInDropboxPersonalAccount(String dropboxPath) {
    String pathError = DbxPathV2.findError(dropboxPath);
    if (pathError != null) {
      return null;
    }

    try {
      return dbxClient.files().deleteV2(dropboxPath);
    } catch (DbxException e) {
      System.out.println(e.getMessage());
      return null;
    }
  }

  public FullAccount getInfoAccount() {
    try {
      return dbxClient.users().getCurrentAccount();
    } catch (DbxException ex) {
      System.out.println(ex.getMessage());
      return null;
    }
  }
}
