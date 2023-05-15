package com.notecap.capture;

/**
 * @author juanvladimir13 <juanvladimir13@gmail.com>
 * @see https://github.com/juanvladimir13
 */

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Capture {
  private String getDateFormatString() {
    return new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss").format(new Date());
  }

  public Rectangle getScreenSize() {
    Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    return new Rectangle(screenSize);
  }

  public BufferedImage takeAScreenshot() {
    try {
      return new Robot().createScreenCapture(getScreenSize());
    } catch (AWTException awtException) {
      return null;
    }
  }

  /**
   * Crea una captura de pantalla y almacena la imagen en el disco duro
   *
   * @param folderPath      ruta absoluta o relativa de la carpeta
   * @param filename        nombre del archivo sin extencion
   * @param imageFormatName nombre del formato de imagen, puede ser jpg o png
   * @return nombre del archivo con la ruta absoluta {@code String},
   * {@code null} error en el proceso
   */
  public String captureImageAndSaveInLocalStorage(String folderPath, String filename, String imageFormatName) {
    if (folderPath.isEmpty() || imageFormatName.isEmpty())
      return null;

    BufferedImage bufferedImage = takeAScreenshot();
    if (bufferedImage == null) return null;

    String pathname = getFullPathName(folderPath, filename, imageFormatName);
    return writeImageInLocalStorage(bufferedImage, pathname, imageFormatName) ? pathname : null;
  }

  public boolean writeImageInLocalStorage(BufferedImage bufferedImage, String pathname, String imageFormatName) {
    try {
      ImageIO.write(bufferedImage, imageFormatName, new File(pathname));
    } catch (IllegalArgumentException | IOException err) {
      return false;
    }
    return true;
  }

  private String getFullPathName(String folderPath, String filename, String imageFormatName) {
    if (!imageFormatName.equals("png") && !imageFormatName.equals("jpg")) {
      imageFormatName = "png";
    }

    return String.format("%s/%s%s.%s",
        folderPath,
        filename.isEmpty() ? "" : filename + "_",
        getDateFormatString(),
        imageFormatName
    );
  }
}
