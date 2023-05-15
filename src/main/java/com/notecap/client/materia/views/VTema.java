package com.notecap.client.materia.views;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import com.notecap.client.utils.ComboBoxItem;
import com.notecap.client.utils.ComboBoxManager;
import com.notecap.client.utils.DataModel;

public class VTema extends javax.swing.JFrame {

  private String id = "0";

  public VTema() {
    initComponents();
    txtCarpeta.setModel(new ComboBoxManager());
    txtPeriodoAcademico.setModel(new ComboBoxManager());
  }

  public Map<String, String> capturarDatos() {
    Map<String, String> formulario = new HashMap<>();
    formulario.put("id", id);
    formulario.put("fecha", txtFecha.getText());
    formulario.put("codigo", txtCodigo.getText());
    formulario.put("titulo", txtTitulo.getText());
    formulario.put("periodo_academico_id", ((ComboBoxItem) txtPeriodoAcademico.getModel().getSelectedItem()).getId());
    formulario.put("carpeta_id", ((ComboBoxItem) txtCarpeta.getModel().getSelectedItem()).getId());

    return formulario;
  }

  public void mostrarDatos(Map<String, String> datos) {
    id = datos.get("id");
    txtFecha.setText(datos.get("fecha"));
    txtCodigo.setText(datos.get("codigo"));
    txtTitulo.setText(datos.get("titulo"));

    txtPeriodoAcademico.getModel().setSelectedItem(new ComboBoxItem(datos.get("periodo_academico_id"), datos.get("periodo_academico")));
    txtCarpeta.getModel().setSelectedItem(new ComboBoxItem(datos.get("carpeta_id"), datos.get("carpeta")));
  }

  public void cargarListado(List<Map<String, String>> data) {
    String[] columnName = {"Fecha", "Titulo", "P Academico", "Carpeta"};
    String[] dataColumnName = {"fecha", "titulo", "periodo_academico_id", "carpeta_id"};
    DataModel dataModel = new DataModel(columnName, data, dataColumnName);
    tblTema.setModel(dataModel);
  }

  public void limpiar() {
    id = "0";
    txtFecha.setText("");
    txtCodigo.setText("");
    txtTitulo.setText("");
    txtPeriodoAcademico.setSelectedIndex(-1);
    txtCarpeta.setSelectedIndex(-1);
  }

  public void cargarCarpetaComboBox(Map<String, String> carpetas) {
    if (carpetas.isEmpty()) {
      txtCarpeta.setEnabled(false);
      return;
    }

    ((ComboBoxManager) txtCarpeta.getModel()).setDataList(carpetas);
    txtCarpeta.setEnabled(true);
  }

  public void cargarPeriodoAcademicoComboBox(Map<String, String> materias) {
    if (materias.isEmpty()) {
      txtPeriodoAcademico.setEnabled(false);
      return;
    }

    ((ComboBoxManager) txtPeriodoAcademico.getModel()).setDataList(materias);
    txtPeriodoAcademico.setEnabled(true);
  }

  public String getId() {
    int row = tblTema.getSelectedRow();
    id = ((DataModel) tblTema.getModel()).getValueAt(row, "id");
    return id;
  }

  public String solicitarDato(String message) {
    return JOptionPane.showInputDialog(this, message);
  }

  public void mensajeError(String message) {
    JOptionPane.showMessageDialog(this, message, "Error :-(", JOptionPane.ERROR_MESSAGE);
  }

  public void mostrarFormulario() {
    java.awt.EventQueue.invokeLater(() -> setVisible(true));
  }

  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jLabel2 = new javax.swing.JLabel();
    btnListar = new javax.swing.JButton();
    btnBuscar = new javax.swing.JButton();
    btnGuardar = new javax.swing.JButton();
    txtTitulo = new javax.swing.JTextField();
    jLabel4 = new javax.swing.JLabel();
    txtCodigo = new javax.swing.JTextField();
    jLabel1 = new javax.swing.JLabel();
    jLabel3 = new javax.swing.JLabel();
    txtPeriodoAcademico = new javax.swing.JComboBox<>();
    jScrollPane2 = new javax.swing.JScrollPane();
    tblTema = new javax.swing.JTable();
    jLabel5 = new javax.swing.JLabel();
    jLabel6 = new javax.swing.JLabel();
    txtFecha = new javax.swing.JTextField();
    btnEliminar = new javax.swing.JButton();
    txtCarpeta = new javax.swing.JComboBox<>();
    btnNuevo = new javax.swing.JButton();

    setTitle("Tema");

    jLabel2.setFont(new java.awt.Font("Droid Sans", 1, 18)); // NOI18N
    jLabel2.setText("Gestionar tema");

    btnListar.setText("Listar");

    btnBuscar.setText("Buscar");

    btnGuardar.setText("Guardar");

    txtTitulo.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N

    jLabel4.setText("Titulo");

    txtCodigo.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N

    jLabel1.setText("Codigo");

    jLabel3.setText("Periodo academico");

    txtPeriodoAcademico.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N

    tblTema.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
    tblTema.setModel(new javax.swing.table.DefaultTableModel(
      new Object [][] {
        {null, null, null},
        {null, null, null},
        {null, null, null},
        {null, null, null}
      },
      new String [] {
        "Fecha", "Codigo", "Titulo"
      }
    ) {
      Class[] types = new Class [] {
        java.lang.String.class, java.lang.String.class, java.lang.String.class
      };
      boolean[] canEdit = new boolean [] {
        false, false, false
      };

      public Class getColumnClass(int columnIndex) {
        return types [columnIndex];
      }

      public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
      }
    });
    tblTema.setRowHeight(25);
    tblTema.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    jScrollPane2.setViewportView(tblTema);

    jLabel5.setText("Carpeta");

    jLabel6.setText("Fecha");

    txtFecha.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N

    btnEliminar.setText("Eliminar");

    txtCarpeta.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N

    btnNuevo.setText("Nuevo");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(jScrollPane2)
              .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnNuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnListar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap())
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(jLabel3)
                  .addComponent(txtPeriodoAcademico, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(layout.createSequentialGroup()
                    .addComponent(jLabel5)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 349, Short.MAX_VALUE))
                  .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(txtCarpeta, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE))))
              .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(jLabel6)
                  .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                  .addComponent(jLabel1)
                  .addComponent(txtCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(txtTitulo)
                  .addComponent(jLabel4))))
            .addGap(197, 197, 197))))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel2)
          .addComponent(btnGuardar)
          .addComponent(btnBuscar)
          .addComponent(btnListar)
          .addComponent(btnEliminar)
          .addComponent(btnNuevo))
        .addGap(18, 18, 18)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
          .addGroup(layout.createSequentialGroup()
            .addComponent(jLabel6)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addGroup(layout.createSequentialGroup()
            .addComponent(jLabel4)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addGroup(layout.createSequentialGroup()
            .addComponent(jLabel1)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
        .addGap(9, 9, 9)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
          .addGroup(layout.createSequentialGroup()
            .addComponent(jLabel3)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(txtPeriodoAcademico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addGroup(layout.createSequentialGroup()
            .addComponent(jLabel5)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(txtCarpeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        .addGap(24, 24, 24)
        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap(257, Short.MAX_VALUE))
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  // Variables declaration - do not modify//GEN-BEGIN:variables
  public javax.swing.JButton btnBuscar;
  public javax.swing.JButton btnEliminar;
  public javax.swing.JButton btnGuardar;
  public javax.swing.JButton btnListar;
  public javax.swing.JButton btnNuevo;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel jLabel5;
  private javax.swing.JLabel jLabel6;
  private javax.swing.JScrollPane jScrollPane2;
  public javax.swing.JTable tblTema;
  public javax.swing.JComboBox<com.notecap.client.utils.ComboBoxItem> txtCarpeta;
  public javax.swing.JTextField txtCodigo;
  public javax.swing.JTextField txtFecha;
  public javax.swing.JComboBox<com.notecap.client.utils.ComboBoxItem> txtPeriodoAcademico;
  public javax.swing.JTextField txtTitulo;
  // End of variables declaration//GEN-END:variables
}
