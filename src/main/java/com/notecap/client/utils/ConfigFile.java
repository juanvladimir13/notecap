package com.notecap.client.utils;

import java.io.*;
import java.util.Properties;

/**
 * @author juanvladimir13
 * @version 1.3
 * @see https://www.github.com/juanvladimir13
 */
public final class ConfigFile {
  private static final String NAME_FILE = "notecap.properties";
  private String dropboxFolder;
  private String captureImageFormat;
  private String captureFolderPath;
  private String captureFilenamePrefix;
  private String captureDataId;

  public ConfigFile() {
    this.dropboxFolder = "/modulo1";
    this.captureImageFormat = "png";
    this.captureFolderPath = "./resources/images";
    this.captureFilenamePrefix = "";
    this.captureDataId = "0";

    if (!verifyProperties()){
      writeProperties();
    } else{
      loadProperties();
    }
  }

  private boolean verifyProperties() {
    Properties properties = new Properties();
    InputStream input = openFileConfig();
    if (input == null) return false;

    try {
      properties.load(input);
    } catch (IOException e) {
      return false;
    }

    return properties.containsKey("dropboxAuthApp") &&
        properties.containsKey("dropboxAuthUser") &&
        properties.containsKey("dropboxFolder") &&
        properties.containsKey("captureImageFormat") &&
        properties.containsKey("captureFolderPath") &&
        properties.containsKey("captureFilenamePrefix") &&
        properties.containsKey("captureDataId");
  }

  public boolean writeProperties() {
    Properties properties = new Properties();
    try {
      OutputStream output = new FileOutputStream(NAME_FILE);

      properties.setProperty("dropboxFolder", dropboxFolder);
      properties.setProperty("captureImageFormat", captureImageFormat);
      properties.setProperty("captureFolderPath", captureFolderPath);
      properties.setProperty("captureFilenamePrefix", captureFilenamePrefix);
      properties.setProperty("captureDataId", captureDataId);

      properties.store(output, null);

    } catch (IOException err) {
      return false;
    }
    return false;
  }

  public void loadProperties() {
    Properties properties = new Properties();
    InputStream input = openFileConfig();

    if (input == null) return;

    try {
      properties.load(input);

      dropboxFolder = properties.getProperty("dropboxFolder");
      captureImageFormat = properties.getProperty("captureImageFormat");
      captureFolderPath = properties.getProperty("captureFolderPath");
      captureFilenamePrefix = properties.getProperty("captureFilenamePrefix");
      captureDataId = properties.getProperty("captureDataId");
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  private InputStream openFileConfig() {
    try {
      InputStream inputStream = new FileInputStream(NAME_FILE);
      return inputStream;
    } catch (FileNotFoundException e) {
      System.out.println(e.getMessage());
      return null;
    }
  }

  public String getDropboxFolder() {
    return dropboxFolder;
  }

  public void setDropboxFolder(String dropboxFolder) {
    this.dropboxFolder = dropboxFolder;
  }

  public String getCaptureImageFormat() {
    return captureImageFormat;
  }

  public void setCaptureImageFormat(String captureImageFormat) {
    this.captureImageFormat = captureImageFormat;
  }

  public String getCaptureFolderPath() {
    return captureFolderPath;
  }

  public void setCaptureFolderPath(String captureFolderPath) {
    this.captureFolderPath = captureFolderPath;
  }

  public String getCaptureFilenamePrefix() {
    return captureFilenamePrefix;
  }

  public void setCaptureFilenamePrefix(String captureFilenamePrefix) {
    this.captureFilenamePrefix = captureFilenamePrefix;
  }

  public String getCaptureDataId() {
    return captureDataId;
  }

  public void setCaptureDataId(String captureDataId) {
    this.captureDataId = captureDataId;
  }

  private Properties getProperties() {
    Properties properties = new Properties();
    InputStream input = null;
    try {
      input = new FileInputStream(NAME_FILE);
      properties.load(input);
    } catch (IOException ex) {
      ex.printStackTrace();
      properties = null;
    } finally {
      if (input != null) {
        try {
          input.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }

    return properties;
  }
}
