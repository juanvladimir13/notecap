package com.notecap.client.materia.controllers;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;
import com.notecap.client.cloud.models.MCarpeta;
import com.notecap.client.materia.models.MDocente;
import com.notecap.client.materia.models.MMateria;
import com.notecap.client.materia.models.MPeriodoAcademico;
import com.notecap.client.materia.views.VPeriodoAcademico;

public class CPeriodoAcademico {

  private MPeriodoAcademico modelo;
  private VPeriodoAcademico vista;

  private MMateria materia;
  private MDocente docente;
  private MCarpeta carpeta;

  public CPeriodoAcademico() {
    this.modelo = new MPeriodoAcademico();
    this.vista = new VPeriodoAcademico();

    this.materia = new MMateria();
    this.docente = new MDocente();
    this.carpeta = new MCarpeta();

    bindFormToMethod();
  }

  private void bindFormToMethod() {
    vista.btnNuevo.addActionListener(e -> nuevo());
    vista.btnEliminar.addActionListener(e -> eliminar());
    vista.btnListar.addActionListener(e -> listar());
    vista.btnBuscar.addActionListener(e -> buscar());
    vista.btnGuardar.addActionListener(e -> guardar());

    vista.tblMateria.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent evt) {
        loadDataTable();
      }
    });

    vista.tblMateria.addKeyListener(new KeyAdapter() {
      @Override
      public void keyReleased(KeyEvent evt) {
        loadDataTable();
      }
    });
  }

  private void nuevo() {
    materiaComboBox();
    docenteComboBox();
    carpetaComboBox();
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
    String criterio = vista.solicitarDato("Buscar por nombre: ");
    fetch("nombre", criterio);
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

  public void mostrarFormulario() {
    materiaComboBox();
    docenteComboBox();
    carpetaComboBox();
    vista.cargarListado(modelo.listado());
    vista.mostrarFormulario();
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
    fetch("id", vista.getId());
  }

  private void docenteComboBox() {
    Map<String, String> datos = docente.comboBox();
    vista.cargarDocenteComboBox(datos);
  }

  private void materiaComboBox() {
    Map<String, String> datos = materia.comboBox();
    vista.cargarMateriaComboBox(datos);
  }

  private void carpetaComboBox() {
    Map<String, String> datos = carpeta.comboBox();
    vista.cargarCarpetaComboBox(datos);
  }
}
