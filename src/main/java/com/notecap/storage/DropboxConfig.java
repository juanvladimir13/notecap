package com.notecap.storage;

import com.dropbox.core.DbxAppInfo;
import com.dropbox.core.DbxAuthInfo;
import com.dropbox.core.json.JsonReader;
import com.dropbox.core.oauth.DbxCredential;

/**
 * @author Juan Vladimir
 * @see <a href="https://github.com/juanvladimir13">github</a>
 */
public class DropboxConfig {
  public static String DROPBOX_AUTH_APP = "./config/dropbox.app";
  public static String DROPBOX_AUTH_USER = "./config/user.auth";

  public static DbxAuthInfo readAuthInfoFile() {
    try {
      return DbxAuthInfo.Reader.readFromFile(DropboxConfig.DROPBOX_AUTH_USER);
    } catch (JsonReader.FileLoadException ex) {
      System.out.println(ex.getMessage());
      return null;
    }
  }

  public static DbxAppInfo readAppInfoFile() {
    try {
      return DbxAppInfo.Reader.readFromFile(DropboxConfig.DROPBOX_AUTH_APP);
    } catch (JsonReader.FileLoadException ex) {
      System.out.println(ex.getMessage());
      return null;
    }
  }

  public static DbxCredential readCredential() {
    try {
      return DbxCredential.Reader.readFromFile(DropboxConfig.DROPBOX_AUTH_USER);
    } catch (JsonReader.FileLoadException ex) {
      System.out.println(ex.getMessage());
      return null;
    }
  }
}
