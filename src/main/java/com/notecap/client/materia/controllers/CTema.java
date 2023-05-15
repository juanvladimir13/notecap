package com.notecap.client.materia.controllers;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;
import com.notecap.client.cloud.models.MCarpeta;
import com.notecap.client.materia.models.MMateria;
import com.notecap.client.materia.models.MTema;
import com.notecap.client.materia.views.VTema;

public class CTema {

  private MTema modelo;
  private VTema vista;

  private MMateria materia;
  private MCarpeta carpeta;

  public CTema() {
    this.modelo = new MTema();
    this.vista = new VTema();

    this.carpeta = new MCarpeta();
    this.materia = new MMateria();

    bindFormToMethod();
  }

  private void bindFormToMethod() {
    vista.btnNuevo.addActionListener(l -> nuevo());
    vista.btnEliminar.addActionListener(e -> eliminar());
    vista.btnListar.addActionListener(e -> listar());
    vista.btnBuscar.addActionListener(l -> buscar());
    vista.btnGuardar.addActionListener(l -> guardar());

    vista.tblTema.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent evt) {
        loadDataTable();
      }
    });

    vista.tblTema.addKeyListener(new KeyAdapter() {
      @Override
      public void keyReleased(KeyEvent evt) {
        loadDataTable();
      }
    });
  }

  private void nuevo() {
    carpetaComboBox();
    materiaComboBox();
    vista.limpiar();
  }

  private void guardar() {
    Map<String, String> datos = vista.capturarDatos();
    modelo.cargarDatos(datos);

    if (!modelo.guardar()) {
      vista.mensajeError("Datos no guardados");
      return;
    }

    vista.cargarListado(modelo.listado());
    vista.limpiar();
  }

  private void listar() {
    vista.cargarListado(modelo.listado());
  }

  private void buscar() {
    String criterio = vista.solicitarDato("Buscar por titulo: ");
    fetch("titulo", criterio);
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

  private void eliminar() {
    Map<String, String> datos = vista.capturarDatos();
    if (!modelo.eliminar(datos.get("id"))) {
      vista.mensajeError("Error al eliminar datos");
      return;
    }

    vista.limpiar();
    vista.cargarListado(modelo.listado());
  }

  private void carpetaComboBox() {
    Map<String, String> datos = carpeta.comboBox();
    vista.cargarCarpetaComboBox(datos);
  }

  private void materiaComboBox() {
    Map<String, String> datos = materia.comboBox();
    vista.cargarPeriodoAcademicoComboBox(datos);
  }

  public void mostrarFormulario() {
    carpetaComboBox();
    materiaComboBox();
    vista.cargarListado(modelo.listado());
    vista.mostrarFormulario();
  }

  public void loadDataTable() {
    fetch("id", vista.getId());
  }
}
