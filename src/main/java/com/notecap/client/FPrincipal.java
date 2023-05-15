package com.notecap.client;

import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.SystemTray;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

import com.notecap.client.cloud.controllers.CArchivo;
import com.notecap.client.cloud.controllers.CCarpeta;
import com.notecap.client.cloud.controllers.CCuenta;
import com.notecap.client.utils.TrayIconManage;
import com.notecap.client.materia.controllers.CDocente;
import com.notecap.client.materia.controllers.CMateria;
import com.notecap.client.materia.controllers.CPeriodoAcademico;
import com.notecap.client.materia.controllers.CTema;

public class FPrincipal extends javax.swing.JFrame {

  public static MenuItem captureAppItem = new MenuItem("Capture");

  private CCarpeta carpeta;
  private CArchivo imagen;

  private CDocente docente;
  private CMateria materia;
  private CPeriodoAcademico periodoAcademico;
  private CTema tema;

  public FPrincipal() {
    initComponents();
    addWindowListener(new WindowAdapter() {
      @Override
      public void windowIconified(WindowEvent e) {
        setVisible(false);
      }
    });
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    if (!SystemTray.isSupported()) {
      return;
    }

    packModules();
    packSystemTray();
  }

  private void packModules() {
    carpeta = new CCarpeta();
    imagen = new CArchivo();

    docente = new CDocente();
    materia = new CMateria();
    periodoAcademico = new CPeriodoAcademico();
    tema = new CTema();

    CCuenta cuenta = new CCuenta();
    FPrincipal.labAccount.setText(cuenta.loadAccountInfoToFooter());
  }

  private void packSystemTray() {
    MenuItem exitAppItem = new MenuItem("Salir");
    exitAppItem.addActionListener((ActionEvent arg0) -> {
      System.exit(0);
    });

    MenuItem restoreAppItem = new MenuItem("Restaurar");
    restoreAppItem.addActionListener((ActionEvent arg0) -> {
      if (getExtendedState() == JFrame.ICONIFIED || getExtendedState() == 7) {
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        repaint();
      }
      System.out.println(getExtendedState());
    });

    TrayIconManage trayIcon;
    SystemTray systemtray;
    trayIcon = new TrayIconManage();
    trayIcon.addMenuItem(captureAppItem);
    trayIcon.addMenuItem(restoreAppItem);
    trayIcon.addMenuItem(exitAppItem);
    trayIcon.pack("Dropbox");

    systemtray = SystemTray.getSystemTray();
    try {
      systemtray.add(trayIcon.getTrayIcon());
    } catch (AWTException ex) {
    }
  }

  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    labAccount = new javax.swing.JLabel();
    jMenuBar1 = new javax.swing.JMenuBar();
    menCloud = new javax.swing.JMenu();
    menFormAuth = new javax.swing.JMenuItem();
    menFormCarpeta = new javax.swing.JMenuItem();
    menFormCapture = new javax.swing.JMenuItem();
    menMateria = new javax.swing.JMenu();
    menFormDocente = new javax.swing.JMenuItem();
    menFormMateria = new javax.swing.JMenuItem();
    menFormPeriodoAcademico = new javax.swing.JMenuItem();
    menFormTema = new javax.swing.JMenuItem();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setTitle("Capture dropbox");

    labAccount.setBackground(new java.awt.Color(255, 255, 204));
    labAccount.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
    labAccount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    labAccount.setText(".");

    menCloud.setText("Cloud");

    menFormAuth.setText("Autorizar acceso de aplicacion");
    menFormAuth.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        menFormAuthActionPerformed(evt);
      }
    });
    menCloud.add(menFormAuth);

    menFormCarpeta.setText("Crear carpeta");
    menFormCarpeta.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        menFormCarpetaActionPerformed(evt);
      }
    });
    menCloud.add(menFormCarpeta);

    menFormCapture.setText("Configurar captura de pantalla");
    menFormCapture.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        menFormCaptureActionPerformed(evt);
      }
    });
    menCloud.add(menFormCapture);

    jMenuBar1.add(menCloud);

    menMateria.setText("Materia");

    menFormDocente.setText("Registrar docente");
    menFormDocente.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        menFormDocenteActionPerformed(evt);
      }
    });
    menMateria.add(menFormDocente);

    menFormMateria.setText("Registrar materia");
    menFormMateria.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        menFormMateriaActionPerformed(evt);
      }
    });
    menMateria.add(menFormMateria);

    menFormPeriodoAcademico.setText("Periodo academico");
    menFormPeriodoAcademico.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        menFormPeriodoAcademicoActionPerformed(evt);
      }
    });
    menMateria.add(menFormPeriodoAcademico);

    menFormTema.setText("Registrar tema");
    menFormTema.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        menFormTemaActionPerformed(evt);
      }
    });
    menMateria.add(menFormTema);

    jMenuBar1.add(menMateria);

    setJMenuBar(jMenuBar1);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(labAccount, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        .addGap(0, 260, Short.MAX_VALUE)
        .addComponent(labAccount))
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void menFormCarpetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menFormCarpetaActionPerformed
    carpeta.mostrarFormulario();
  }//GEN-LAST:event_menFormCarpetaActionPerformed

  private void menFormDocenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menFormDocenteActionPerformed
    docente.mostrarFormulario();
  }//GEN-LAST:event_menFormDocenteActionPerformed

  private void menFormMateriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menFormMateriaActionPerformed
    materia.mostrarFormulario();
  }//GEN-LAST:event_menFormMateriaActionPerformed

  private void menFormTemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menFormTemaActionPerformed
    tema.mostrarFormulario();
  }//GEN-LAST:event_menFormTemaActionPerformed

  private void menFormAuthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menFormAuthActionPerformed
    CCuenta cuenta = new CCuenta();
    cuenta.mostrarFormulario();
  }//GEN-LAST:event_menFormAuthActionPerformed

  private void menFormCaptureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menFormCaptureActionPerformed
    imagen.mostrarFormulario();
  }//GEN-LAST:event_menFormCaptureActionPerformed

  private void menFormPeriodoAcademicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menFormPeriodoAcademicoActionPerformed
    periodoAcademico.mostrarFormulario();
  }//GEN-LAST:event_menFormPeriodoAcademicoActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JMenuBar jMenuBar1;
  public static javax.swing.JLabel labAccount;
  private javax.swing.JMenu menCloud;
  private javax.swing.JMenuItem menFormAuth;
  private javax.swing.JMenuItem menFormCapture;
  private javax.swing.JMenuItem menFormCarpeta;
  private javax.swing.JMenuItem menFormDocente;
  private javax.swing.JMenuItem menFormMateria;
  private javax.swing.JMenuItem menFormPeriodoAcademico;
  private javax.swing.JMenuItem menFormTema;
  private javax.swing.JMenu menMateria;
  // End of variables declaration//GEN-END:variables
}
