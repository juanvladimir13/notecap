package com.notecap.client;

import com.notecap.storage.AccountInfo;
import com.notecap.storage.DropboxClient;
import com.notecap.storage.DropboxUpload;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class StorageTest {
  @Test
  public void loadAccountInfoUser() {
    AccountInfo accountInfo = new AccountInfo();
    assertTrue(accountInfo.loadAccountInfo());
  }

  @Test
  public void createAndDeleteFolder() {
    assertNotNull(
        DropboxClient.getInstance()
            .createFolderInDropboxPersonalAccount("", "test")
    );

    assertNotNull(
        DropboxClient.getInstance()
            .deleteFolderInDropboxPersonalAccount("/test")
    );
  }

  @Test
  public void uploadFile(){
    assertNotNull(
        DropboxUpload
            .uploadFileChunkedToCloudStorage(
                "./config/user.auth", "", "user.auth"
            )
    );

    assertNotNull(
        DropboxClient.getInstance()
            .deleteFolderInDropboxPersonalAccount("/user.auth")
    );
  }
}
