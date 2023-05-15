package com.notecap.storage;

/**
 * @author juanvladimir13 <juanvladimir13@gmail.com>
 * @see https://github.com/juanvladimir13
 */

import com.dropbox.core.*;
import com.dropbox.core.oauth.DbxCredential;

import javax.swing.JOptionPane;
import java.io.File;
import java.io.IOException;

public class DropboxAuth {
  public static boolean authorizeAppToAccountPersonalDropBox() {
    DbxAppInfo appInfo = DropboxConfig.readAppInfoFile();
    if (appInfo == null) return false;

    DbxAuthFinish authFinish = getAccessTokenTheApp(appInfo);
    if (authFinish == null) return false;

    return writeAppCredential(appInfo, authFinish);
  }

  private static DbxAuthFinish getAccessTokenTheApp(DbxAppInfo appInfo) {
    DbxAppInfo appInfoWithoutSecret = new DbxAppInfo(appInfo.getKey());
    DbxPKCEWebAuth pkceWebAuth = new DbxPKCEWebAuth(DropboxClient.getInstance().getRequestConfig(), appInfoWithoutSecret);

    DbxWebAuth.Request webAuthRequest = DbxWebAuth.newRequestBuilder()
        .withNoRedirect()
        .withTokenAccessType(TokenAccessType.OFFLINE)
        .build();

    String authorizeUrl = pkceWebAuth.authorize(webAuthRequest);
    ManageBrowser.openUrlInBrowserDefault(authorizeUrl);
    String code = JOptionPane.showInputDialog("Authorization code", "Enter the authorization:");

    try {
      return pkceWebAuth.finishFromCode(code.trim());
    } catch (DbxException ex) {
      System.out.println(ex.getMessage());
      return null;
    }
  }

  private static boolean writeAppCredential(DbxAppInfo appInfo, DbxAuthFinish authFinish) {
    DbxCredential credential = new DbxCredential(authFinish.getAccessToken(), authFinish
        .getExpiresAt(), authFinish.getRefreshToken(), appInfo.getKey(), appInfo.getSecret());

    File output = new File(DropboxConfig.DROPBOX_AUTH_USER);
    try {
      DbxCredential.Writer.writeToFile(credential, output);
    } catch (IOException ex) {
      System.out.println(ex.getMessage());
      try {
        DbxCredential.Writer.writeToStream(credential, System.err);
      } catch (IOException e) {
        System.out.println(e.getMessage());
        return false;
      }
      return false;
    }
    return true;
  }
}
