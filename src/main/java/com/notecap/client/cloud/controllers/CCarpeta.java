package com.notecap.client.cloud.controllers;

import com.notecap.storage.DropboxClient;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import com.notecap.client.cloud.models.MCarpeta;
import com.notecap.client.cloud.views.VCarpeta;
import com.notecap.storage.dto.FolderInfo;

public class CCarpeta {

  private MCarpeta modelo;
  private VCarpeta vista;

  public CCarpeta() {
    this.modelo = new MCarpeta();
    this.vista = new VCarpeta();
    initListener();
  }

  private void initListener() {
    vista.btnNuevo.addActionListener(l -> nuevo());
    vista.btnListar.addActionListener(e -> listar());
    vista.btnBuscar.addActionListener(l -> buscar());
    vista.btnGuardar.addActionListener(l -> crearCarpeta());
    vista.btnEliminar.addActionListener(l -> eliminar());

    vista.tblCarpeta.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent evt) {
        loadDataTable();
      }
    });

    vista.tblCarpeta.addKeyListener(new KeyAdapter() {
      @Override
      public void keyReleased(KeyEvent evt) {
        loadDataTable();
      }
    });
  }

  private void nuevo() {
    vista.limpiar();
  }

  private void crearCarpeta() {
    Map<String, String> datos = vista.capturarDatos();

    FolderInfo folderInfo = DropboxClient.getInstance()
        .createFolderInDropboxPersonalAccount(
            datos.get("path_display"),
            datos.get("name")
        );

    if (folderInfo == null) {
      vista.mensajeError("Error al crear carpeta en Dropbox");
      return;
    }

    guardar(folderInfo);
  }

  private void guardar(FolderInfo folderInfo) {
    Map<String, String> datos = new HashMap<>();
    datos.put("id", folderInfo.id);
    datos.put("name", folderInfo.name);
    datos.put("path_lower", folderInfo.path_lower);
    datos.put("path_display", folderInfo.path_display);

    modelo.cargarDatos(datos);

    if (!modelo.guardar()) {
      vista.mensajeError("Datos no guardados");
      return;
    }

    vista.cargarListado(modelo.listado());
    vista.limpiar();
  }

  private void eliminar() {
    Map<String, String> datos = vista.capturarDatos();
    if (DropboxClient.getInstance()
        .deleteFolderInDropboxPersonalAccount(datos.get("path_display")) == null) {
      return;
    }

    if (modelo.eliminar(datos.getOrDefault("id", "0"))) {
      vista.limpiar();
      vista.cargarListado(modelo.listado());
    }
  }

  private void listar() {
    vista.cargarListado(modelo.listado());
  }

  private void buscar() {
    String criterio = vista.solicitarDato("Buscar por nombre: ");
    fetch("name", criterio);
  }

  private void fetch(String column, String value) {
    if (value == null) {
      return;
    }

    Map<String, String> datos = modelo.buscar(column, value);
    if (datos == null) {
      vista.mensajeError("Datos no encontrados");
      return;
    }

    vista.mostrarDatos(datos);
  }

  public void loadDataTable() {
    String criterio = vista.getId();
    fetch("id", criterio);
  }

  public void mostrarFormulario() {
    vista.cargarListado(modelo.listado());
    vista.mostrarFormulario();
  }
}
