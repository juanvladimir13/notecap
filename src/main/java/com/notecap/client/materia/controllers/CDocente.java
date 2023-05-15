package com.notecap.client.materia.controllers;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;
import com.notecap.client.materia.models.MDocente;
import com.notecap.client.materia.views.VDocente;

public class CDocente {

  private MDocente modelo;
  private VDocente vista;

  public CDocente() {
    this.modelo = new MDocente();
    this.vista = new VDocente();
    bindFormToMethod();
  }

  private void bindFormToMethod() {
    vista.btnNuevo.addActionListener(l -> nuevo());
    vista.btnEliminar.addActionListener(l -> eliminar());
    vista.btnListar.addActionListener(l -> listar());
    vista.btnBuscar.addActionListener(l -> buscar());
    vista.btnGuardar.addActionListener(l -> guardar());

    vista.tblDocente.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent evt) {
        loadDataTable();
      }
    });

    vista.tblDocente.addKeyListener(new KeyAdapter() {
      @Override
      public void keyReleased(KeyEvent evt) {
        loadDataTable();
      }
    });
  }

  private void nuevo() {
    vista.limpiar();
  }

  private void guardar() {
    String docente_id = guardarModel();
    
    if (docente_id != null) {
      vista.cargarListado(modelo.listado());
    }
  }

  private String guardarModel() {
    Map<String, String> datos = vista.capturarDatos();
    modelo.cargarDatos(datos);
    
    if (!modelo.guardar()) {
      vista.mensajeError("Datos no guardados");
      return null;
    }
    
    return modelo.buscar("email", datos.get("email")).getOrDefault("id", null);
  }

  private void listar() {
    vista.cargarListado(modelo.listado());
  }

  private void buscar() {
    String criterio = vista.solicitarDato("Buscar por apellidos: ");
    fetch("apellidos", criterio);
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
    if (modelo.eliminar(vista.capturarDatos().get("id"))) {
      vista.limpiar();
      vista.cargarListado(modelo.listado());
    };
  }

  public void loadDataTable() {
    fetch("id", vista.getId());
  }

  public void mostrarFormulario() {
    vista.cargarListado(modelo.listado());
    vista.mostrarFormulario();
  }
}
