package com.notecap.client.cloud.controllers;

import com.notecap.capture.Capture;
import com.notecap.client.Config;
import com.notecap.client.utils.ConfigFile;
import com.notecap.storage.DropboxUpload;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import com.notecap.client.FPrincipal;
import com.notecap.client.cloud.models.MArchivo;
import com.notecap.client.cloud.views.VArchivo;
import com.notecap.client.materia.models.MTema;
import com.notecap.storage.dto.FileMetadataInfo;

public class CArchivo {

  private MArchivo modelo;
  private VArchivo vista;

  private MTema modelTema;

  private Capture capture;

  public CArchivo() {
    this.modelo = new MArchivo();
    this.vista = new VArchivo();

    this.modelTema = new MTema();

    this.capture = new Capture();

    vista.btnSession.addActionListener(e -> cargarSession());
    vista.txtTema.addActionListener(l -> listar());
    FPrincipal.captureAppItem.addActionListener(l -> capturarPantalla());
  }

  private void cargarSession() {
    Map<String, String> datos = vista.capturarDatos();
    String tema_id = datos.getOrDefault("tema_id", "0");

    if (tema_id == null) {
      vista.mensajeError("Seleccionar un tema");
      return;
    }

    String dropboxFolder = modelTema.getCarpeta(tema_id);
    if (dropboxFolder != null) {
      Config.DROPBOX_FOLDER = dropboxFolder;
    }

    String folder = datos.getOrDefault("carpeta", ".");
    String pathAbsolute = System.getProperty("user.dir") + "/" + folder;
    System.out.println(pathAbsolute);
      if (!new File(pathAbsolute).exists())
        try {
          Files.createDirectories(Paths.get(pathAbsolute));
          folder = "./" + folder.trim();
          vista.mensajeError(folder);
        } catch (IOException ioException) {
          vista.mensajeError("Nombre de carpeta no valido");
          return;
        }

    Config.CAPTURE_FOLDER_PATH = folder;
    Config.CAPTURE_DATA_ID = tema_id;
    Config.CAPTURE_IMAGE_FORMAT = datos.getOrDefault("formato", "png");
    Config.CAPTURE_FILENAME_PREFIX = datos.getOrDefault("nombre", "");

    ConfigFile configFile = new ConfigFile();
    configFile.setDropboxFolder(Config.DROPBOX_FOLDER);
    configFile.setCaptureDataId(Config.CAPTURE_DATA_ID);
    configFile.setCaptureImageFormat(Config.CAPTURE_IMAGE_FORMAT);
    configFile.setCaptureFilenamePrefix(Config.CAPTURE_FILENAME_PREFIX);
    configFile.setCaptureFolderPath(Config.CAPTURE_FOLDER_PATH);
    configFile.writeProperties();
  }

  public synchronized void capturarPantalla() {
    String localPath = capture.captureImageAndSaveInLocalStorage(
        Config.CAPTURE_FOLDER_PATH,
        Config.CAPTURE_FILENAME_PREFIX,
        Config.CAPTURE_IMAGE_FORMAT
    );

    if (localPath == null) {
      vista.mensajeError("Imagen no capturada");
      return;
    }

    String dropboxFilename = new File(localPath).getName();
    FileMetadataInfo dropboxMetadata = DropboxUpload.uploadFileChunkedToCloudStorage(
        localPath,
        Config.DROPBOX_FOLDER,
        dropboxFilename
    );

    if (dropboxMetadata == null) {
      vista.mensajeError("Error al publicar la captura");
      return;
    }
    guardar(dropboxMetadata);
  }

  private void guardar(FileMetadataInfo dropboxMetadata) {
    Map<String, String> datos = new HashMap<>();
    datos.put("id", dropboxMetadata.id);
    datos.put("name", dropboxMetadata.name);
    datos.put("rev", dropboxMetadata.rev);
    datos.put("path_lower", dropboxMetadata.pathLower);
    datos.put("path_display", dropboxMetadata.pathDisplay);
    datos.put("tema_id", Config.CAPTURE_DATA_ID);

    modelo.cargarDatos(datos);

    if (!modelo.guardar()) {
      vista.mensajeError("Datos no guardados");
    }
  }

  private void listar() {
    Map<String, String> datos = vista.capturarDatos();
    vista.cargarListado(modelo.listado(datos.get("tema_id")));
  }

  private void temaComboBox() {
    Map<String, String> datos = modelTema.comboBox();
    vista.cargarTemaComboBox(datos);
  }

  public void mostrarFormulario() {
    temaComboBox();
    vista.mostrarFormulario();
  }
}
