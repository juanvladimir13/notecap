package com.notecap.client.materia.views;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import com.notecap.client.utils.DataModel;

public class VDocente extends javax.swing.JFrame {

  private String id = "0";

  public VDocente() {
    initComponents();
  }

  public Map<String, String> capturarDatos() {
    Map<String, String> formulario = new HashMap<>();
    formulario.put("id", id);
    formulario.put("nombres", txtNombres.getText());
    formulario.put("apellidos", txtApellidos.getText());
    formulario.put("celular", txtCelular.getText());
    formulario.put("email", txtEmail.getText());
    return formulario;
  }


  public void mostrarDatos(Map<String, String> datos) {
    id = datos.get("id");
    txtNombres.setText(datos.get("nombres"));
    txtApellidos.setText(datos.get("apellidos"));
    txtCelular.setText(datos.get("celular"));
    txtEmail.setText(datos.get("email"));
  }

  public void cargarListado(List<Map<String, String>> data) {
    String[] columnName = {"Nombres", "Apellidos", "Celular", "Email"};
    String[] dataColumnName = {"nombres", "apellidos", "celular", "email"};
    DataModel dataModel = new DataModel(columnName, data, dataColumnName);
    tblDocente.setModel(dataModel);
  }

  public String getId() {
    int row = tblDocente.getSelectedRow();
    id = ((DataModel) tblDocente.getModel()).getValueAt(row, "id");
    return id;
  }

  public void limpiar() {
    id = "0";
    txtNombres.setText("");
    txtApellidos.setText("");
    txtCelular.setText("");
    txtEmail.setText("");
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
    txtNombres = new javax.swing.JTextField();
    jLabel1 = new javax.swing.JLabel();
    jLabel3 = new javax.swing.JLabel();
    jScrollPane2 = new javax.swing.JScrollPane();
    tblDocente = new javax.swing.JTable();
    txtApellidos = new javax.swing.JTextField();
    jLabel4 = new javax.swing.JLabel();
    btnEliminar = new javax.swing.JButton();
    btnNuevo = new javax.swing.JButton();
    jLabel6 = new javax.swing.JLabel();
    jLabel7 = new javax.swing.JLabel();
    txtEmail = new javax.swing.JTextField();
    txtCelular = new javax.swing.JTextField();

    setTitle("Docente");

    jLabel2.setFont(new java.awt.Font("Droid Sans", 1, 18)); // NOI18N
    jLabel2.setText("Gestionar docente");

    btnListar.setText("Listar");

    btnBuscar.setText("Buscar");

    btnGuardar.setText("Guardar");

    txtNombres.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N

    jLabel1.setText("Nombres");

    jLabel3.setText("Celular");

    tblDocente.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
    tblDocente.setModel(new javax.swing.table.DefaultTableModel(
      new Object [][] {
        {null, null, null},
        {null, null, null},
        {null, null, null},
        {null, null, null}
      },
      new String [] {
        "Nombres", "Apellidos", "G Academico"
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
    tblDocente.setRowHeight(25);
    tblDocente.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    jScrollPane2.setViewportView(tblDocente);

    txtApellidos.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N

    jLabel4.setText("Apellidos");

    btnEliminar.setText("Eliminar");

    btnNuevo.setText("Nuevo");

    jLabel6.setFont(new java.awt.Font("Droid Sans", 1, 18)); // NOI18N
    jLabel6.setText("Lista de docentes");

    jLabel7.setText("Email");

    txtEmail.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N

    txtCelular.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addComponent(jLabel2)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 118, Short.MAX_VALUE)
            .addComponent(btnNuevo)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(btnEliminar)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(btnListar)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(btnBuscar)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 725, Short.MAX_VALUE)
          .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(jLabel1)
                  .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addComponent(jLabel3)
                  .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(jLabel4)
                  .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addComponent(jLabel7)
                  .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)))
              .addComponent(jLabel6))
            .addGap(0, 0, Short.MAX_VALUE)))
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
          .addGroup(layout.createSequentialGroup()
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
                .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
              .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jLabel3)
            .addGap(32, 32, 32))
          .addGroup(layout.createSequentialGroup()
            .addComponent(jLabel7)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
              .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
        .addGap(18, 18, 18)
        .addComponent(jLabel6)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
        .addGap(235, 235, 235))
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
  private javax.swing.JLabel jLabel6;
  private javax.swing.JLabel jLabel7;
  private javax.swing.JScrollPane jScrollPane2;
  public javax.swing.JTable tblDocente;
  public javax.swing.JTextField txtApellidos;
  public javax.swing.JTextField txtCelular;
  public javax.swing.JTextField txtEmail;
  public javax.swing.JTextField txtNombres;
  // End of variables declaration//GEN-END:variables
}
