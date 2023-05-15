package com.notecap.client;

import com.notecap.client.utils.ConfigFile;
import java.awt.EventQueue;

public class App {

  public static void main(String[] args) {
    ConfigFile configFile = new ConfigFile();

    Config.DROPBOX_FOLDER = configFile.getDropboxFolder();
    Config.CAPTURE_FILENAME_PREFIX = configFile.getCaptureFilenamePrefix();
    Config.CAPTURE_FOLDER_PATH = configFile.getCaptureFolderPath();
    Config.CAPTURE_IMAGE_FORMAT = configFile.getCaptureImageFormat();
    Config.CAPTURE_DATA_ID = configFile.getCaptureDataId();

    EventQueue.invokeLater(() -> {
      new FPrincipal().setVisible(true);
    });
  }
}
