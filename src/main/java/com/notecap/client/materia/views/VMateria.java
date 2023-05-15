package com.notecap.client.materia.views;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import com.notecap.client.utils.ComboBoxItem;
import com.notecap.client.utils.ComboBoxManager;
import com.notecap.client.utils.DataModel;

public class VMateria extends javax.swing.JFrame {

  private String id = "0";

  public VMateria() {
    initComponents();
    txtCarpeta.setModel(new ComboBoxManager());
  }

  public Map<String, String> capturarDatos() {
    Map<String, String> formulario = new HashMap<>();
    formulario.put("id", id);
    formulario.put("nombre", txtNombre.getText());
    formulario.put("nivel", txtNivel.getText());
    formulario.put("carpeta_id", ((ComboBoxItem) txtCarpeta.getModel().getSelectedItem()).getId());

    return formulario;
  }

  public void mostrarDatos(Map<String, String> datos) {
    id = datos.get("id");
    txtNombre.setText(datos.get("nombre"));
    txtNivel.setText(datos.get("nivel"));
    txtCarpeta.getModel().setSelectedItem(new ComboBoxItem(datos.get("carpeta_id"), ""));
  }

  public void cargarCarpetaComboBox(Map<String, String> carpetas) {
    if (carpetas.isEmpty()) {
      txtCarpeta.setEnabled(false);
      return;
    }

    ((ComboBoxManager) txtCarpeta.getModel()).setDataList(carpetas);
    txtCarpeta.setEnabled(true);
  }

  public void cargarListado(List<Map<String, String>> data) {
    String[] columnName = {"Nombre", "Nivel", "Carpeta"};
    String[] dataColumnName = {"nombre", "nivel", "carpeta_id"};
    DataModel dataModel = new DataModel(columnName, data, dataColumnName);
    tblMateria.setModel(dataModel);
  }

  public String getId() {
    int row = tblMateria.getSelectedRow();
    id = ((DataModel) tblMateria.getModel()).getValueAt(row, "id");
    return id;
  }

  public void limpiar() {
    id = "0";
    txtNombre.setText("");
    txtNivel.setText("");
    txtCarpeta.setSelectedItem(-1);
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
    txtNivel = new javax.swing.JTextField();
    jLabel4 = new javax.swing.JLabel();
    txtNombre = new javax.swing.JTextField();
    jLabel1 = new javax.swing.JLabel();
    jScrollPane2 = new javax.swing.JScrollPane();
    tblMateria = new javax.swing.JTable();
    jLabel5 = new javax.swing.JLabel();
    btnEliminar = new javax.swing.JButton();
    txtCarpeta = new javax.swing.JComboBox<>();
    btnNuevo = new javax.swing.JButton();

    setTitle("Materia");

    jLabel2.setFont(new java.awt.Font("Droid Sans", 1, 18)); // NOI18N
    jLabel2.setText("Gestionar materia");

    btnListar.setText("Listar");

    btnBuscar.setText("Buscar");

    btnGuardar.setText("Guardar");

    txtNivel.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N

    jLabel4.setText("Nivel");

    txtNombre.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N

    jLabel1.setText("Nombre");

    tblMateria.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
    tblMateria.setModel(new javax.swing.table.DefaultTableModel(
      new Object [][] {
        {null, null, null},
        {null, null, null},
        {null, null, null},
        {null, null, null}
      },
      new String [] {
        "Nombre", "Nivel", "Carpeta"
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
    tblMateria.setRowHeight(25);
    tblMateria.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    jScrollPane2.setViewportView(tblMateria);

    jLabel5.setText("Carpeta");

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
          .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 887, Short.MAX_VALUE)
          .addGroup(layout.createSequentialGroup()
            .addComponent(jLabel2)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnNuevo)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(btnEliminar)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(btnListar)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(btnBuscar)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(jLabel5)
              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(txtCarpeta, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createSequentialGroup()
                  .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                  .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txtNivel, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))))
            .addGap(0, 0, Short.MAX_VALUE)))
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
          .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
              .addComponent(jLabel2)
              .addComponent(btnGuardar)
              .addComponent(btnBuscar)
              .addComponent(btnListar)
              .addComponent(btnEliminar)
              .addComponent(btnNuevo))
            .addGap(18, 18, 18)
            .addComponent(jLabel1)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addGroup(layout.createSequentialGroup()
            .addComponent(jLabel4)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(txtNivel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jLabel5)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(txtCarpeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(24, 24, 24)
        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap(177, Short.MAX_VALUE))
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
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel jLabel5;
  private javax.swing.JScrollPane jScrollPane2;
  public javax.swing.JTable tblMateria;
  private javax.swing.JComboBox<com.notecap.client.utils.ComboBoxItem> txtCarpeta;
  public javax.swing.JTextField txtNivel;
  public javax.swing.JTextField txtNombre;
  // End of variables declaration//GEN-END:variables
}
