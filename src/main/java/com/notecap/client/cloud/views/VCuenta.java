package com.notecap.client.cloud.views;

import com.notecap.client.FPrincipal;
import com.notecap.storage.AccountInfo;

public class VCuenta extends javax.swing.JFrame {

  public VCuenta() {
    initComponents();
  }

  public void itIsAuthorized (AccountInfo cuenta){
    labId.setText(cuenta.getId());
    labName.setText(cuenta.getDisplayName());
    labCountry.setText(cuenta.getCountry());
    labEmail.setText(cuenta.getEmail());
    labLocale.setText(cuenta.getLocale());

    FPrincipal.labAccount.setText(cuenta.toString());
  }

  public void itIsNotAuthorized(){
    labId.setText("ERROR...");
    labName.setText("ERROR...");
    labCountry.setText("ERROR...");
    labEmail.setText("ERROR...");
    labLocale.setText("ERROR...");

    FPrincipal.labAccount.setText("USUARIO offline !!");
  }

  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jLabel1 = new javax.swing.JLabel();
    jLabel2 = new javax.swing.JLabel();
    btnOpenBrowser = new javax.swing.JButton();
    jLabel7 = new javax.swing.JLabel();
    jLabel8 = new javax.swing.JLabel();
    jLabel9 = new javax.swing.JLabel();
    jLabel3 = new javax.swing.JLabel();
    labName = new javax.swing.JLabel();
    labEmail = new javax.swing.JLabel();
    labLocale = new javax.swing.JLabel();
    labCountry = new javax.swing.JLabel();
    labId = new javax.swing.JLabel();

    setTitle("Archivo");

    jLabel1.setText("Autorizar App");

    jLabel2.setFont(new java.awt.Font("Droid Sans", 1, 18)); // NOI18N
    jLabel2.setText("Autorizar acceso de la APP a cuenta de dropbox");

    btnOpenBrowser.setText("Auth APP");

    jLabel7.setText("1. Go to browser authorize url.");

    jLabel8.setText("2. Click Allow (you might have to log in first).");

    jLabel9.setText("3. Copy the authorization code.");

    jLabel3.setFont(new java.awt.Font("Droid Sans", 1, 18)); // NOI18N
    jLabel3.setText("Account info");

    labName.setFont(new java.awt.Font("DejaVu Sans", 0, 24)); // NOI18N
    labName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    labName.setText("...");
    labName.setToolTipText("");

    labEmail.setFont(new java.awt.Font("DejaVu Sans", 0, 24)); // NOI18N
    labEmail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    labEmail.setText("...");
    labEmail.setToolTipText("");

    labLocale.setFont(new java.awt.Font("DejaVu Sans", 0, 24)); // NOI18N
    labLocale.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    labLocale.setText("...");
    labLocale.setToolTipText("");

    labCountry.setFont(new java.awt.Font("DejaVu Sans", 0, 24)); // NOI18N
    labCountry.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    labCountry.setText("...");
    labCountry.setToolTipText("");

    labId.setFont(new java.awt.Font("DejaVu Sans", 0, 24)); // NOI18N
    labId.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    labId.setText("...");
    labId.setToolTipText("");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(jLabel7)
              .addComponent(jLabel8)
              .addComponent(jLabel9)
              .addComponent(jLabel3))
            .addGap(0, 0, Short.MAX_VALUE))
          .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(jLabel2)
                  .addGroup(layout.createSequentialGroup()
                    .addComponent(jLabel1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnOpenBrowser)))
                .addGap(0, 186, Short.MAX_VALUE))
              .addComponent(labEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
              .addComponent(labLocale, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
              .addComponent(labCountry, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
              .addComponent(labId, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addContainerGap())))
      .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
          .addContainerGap()
          .addComponent(labName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addContainerGap()))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jLabel2)
        .addGap(18, 18, 18)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel1)
          .addComponent(btnOpenBrowser))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(jLabel7)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jLabel8)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jLabel9)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jLabel3)
        .addGap(54, 54, 54)
        .addComponent(labEmail)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(labLocale)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(labCountry)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(labId)
        .addContainerGap(177, Short.MAX_VALUE))
      .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
          .addGap(190, 190, 190)
          .addComponent(labName)
          .addContainerGap(338, Short.MAX_VALUE)))
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  // Variables declaration - do not modify//GEN-BEGIN:variables
  public javax.swing.JButton btnOpenBrowser;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel7;
  private javax.swing.JLabel jLabel8;
  private javax.swing.JLabel jLabel9;
  public javax.swing.JLabel labCountry;
  public javax.swing.JLabel labEmail;
  public javax.swing.JLabel labId;
  public javax.swing.JLabel labLocale;
  public javax.swing.JLabel labName;
  // End of variables declaration//GEN-END:variables
}
