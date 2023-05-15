package com.notecap.client.cloud.views;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

import com.notecap.client.Config;
import com.notecap.client.utils.ComboBoxItem;
import com.notecap.client.utils.ComboBoxManager;
import com.notecap.client.utils.DataModel;

public class VArchivo extends javax.swing.JFrame {

  public VArchivo() {
    initComponents();
    txtTema.setModel(new ComboBoxManager());
    txtCarpeta.setText(Config.CAPTURE_FOLDER_PATH.substring(2));
  }

  public Map<String, String> capturarDatos() {
    Map<String, String> formulario = new HashMap<>();
    formulario.put("carpeta", txtCarpeta.getText());
    formulario.put("nombre", txtNombre.getText());
    formulario.put("formato", txtFormato.getSelectedItem().toString());
    formulario.put("tema_id", ((ComboBoxItem) txtTema.getModel().getSelectedItem()).getId());

    return formulario;
  }

  public void cargarListado(List<Map<String, String>> data) {
    String[] columnData = {"Nombre", "Path", "ID"};
    String[] dataColumnName = {"name", "path_display", "id"};
    DataModel dataModel = new DataModel(columnData, data, dataColumnName);
    tblArchivo.setModel(dataModel);
  }

  public void cargarTemaComboBox(Map<String, String> temas) {
    if (temas.isEmpty()) {
      txtTema.setEnabled(false);
      return;
    }

    ((ComboBoxManager) txtTema.getModel()).setDataList(temas);
    txtTema.setEnabled(true);
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

    txtNombre = new javax.swing.JTextField();
    jLabel1 = new javax.swing.JLabel();
    jLabel2 = new javax.swing.JLabel();
    jLabel3 = new javax.swing.JLabel();
    txtFormato = new javax.swing.JComboBox<>();
    jScrollPane2 = new javax.swing.JScrollPane();
    tblArchivo = new javax.swing.JTable();
    jLabel4 = new javax.swing.JLabel();
    txtTema = new javax.swing.JComboBox<>();
    btnSession = new javax.swing.JButton();
    jLabel5 = new javax.swing.JLabel();
    txtCarpeta = new javax.swing.JTextField();

    setTitle("Archivo");

    txtNombre.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N

    jLabel1.setText("Nombre prefijo de captura");

    jLabel2.setFont(new java.awt.Font("Droid Sans", 1, 18)); // NOI18N
    jLabel2.setText("Captura de pantalla");

    jLabel3.setText("Tema");

    txtFormato.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
    txtFormato.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "png", "jpg" }));

    tblArchivo.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
    tblArchivo.setModel(new javax.swing.table.DefaultTableModel(
      new Object [][] {
        {null, null, null},
        {null, null, null},
        {null, null, null},
        {null, null, null}
      },
      new String [] {
        "Nombre", "Ruta", "Id"
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
    tblArchivo.setRowHeight(25);
    tblArchivo.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    jScrollPane2.setViewportView(tblArchivo);

    jLabel4.setText("Formato imagen");

    txtTema.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N

    btnSession.setText("Cargar session");

    jLabel5.setText("Carpeta local de almacenamiento");

    txtCarpeta.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jScrollPane2)
          .addGroup(layout.createSequentialGroup()
            .addComponent(txtTema, javax.swing.GroupLayout.PREFERRED_SIZE, 489, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
            .addComponent(btnSession))
          .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(jLabel2)
              .addComponent(jLabel3))
            .addGap(0, 0, Short.MAX_VALUE))
          .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(jLabel1)
              .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(jLabel4)
              .addComponent(txtFormato, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addGap(0, 0, Short.MAX_VALUE))
              .addComponent(txtCarpeta))))
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
          .addGroup(layout.createSequentialGroup()
            .addComponent(jLabel2)
            .addGap(18, 18, 18)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
              .addComponent(jLabel1)
              .addComponent(jLabel4))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(txtFormato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
          .addGroup(layout.createSequentialGroup()
            .addComponent(jLabel5)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(txtCarpeta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(4, 4, 4)))
        .addComponent(jLabel3)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(txtTema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(btnSession))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
        .addContainerGap())
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  // Variables declaration - do not modify//GEN-BEGIN:variables
  public javax.swing.JButton btnSession;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel jLabel5;
  private javax.swing.JScrollPane jScrollPane2;
  public javax.swing.JTable tblArchivo;
  private javax.swing.JTextField txtCarpeta;
  private javax.swing.JComboBox<String> txtFormato;
  private javax.swing.JTextField txtNombre;
  public javax.swing.JComboBox<com.notecap.client.utils.ComboBoxItem> txtTema;
  // End of variables declaration//GEN-END:variables
}
