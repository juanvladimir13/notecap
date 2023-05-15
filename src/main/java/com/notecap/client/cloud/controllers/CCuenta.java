package com.notecap.client.cloud.controllers;

import com.notecap.storage.AccountInfo;
import com.notecap.client.cloud.views.VCuenta;
import com.notecap.storage.DropboxAuth;
import com.notecap.storage.DropboxClient;

import javax.swing.JOptionPane;

public class CCuenta {

  private AccountInfo cuenta;
  private VCuenta vista;

  public CCuenta() {
    this.cuenta = new AccountInfo();
    this.vista = new VCuenta();
    vista.btnOpenBrowser.addActionListener(l -> authorizeAppToAccount());
  }

  public String loadAccountInfoToFooter() {
    return cuenta.loadAccountInfo()
        ? cuenta.toString()
        : " USUARIO offline !!";
  }

  public void loadAccountInfoToForm() {
    if (cuenta.loadAccountInfo()) {
      vista.itIsAuthorized(cuenta);
    } else {
      vista.itIsNotAuthorized();
    }
  }

  private void authorizeAppToAccount() {
    if (!DropboxAuth.authorizeAppToAccountPersonalDropBox()) {
      JOptionPane.showMessageDialog(null,
          "Acceso no registrado", "Error :-(",
          JOptionPane.ERROR_MESSAGE
      );
      return;
    }

    JOptionPane.showMessageDialog(null,
        "Acceso registrado", "Registrado :-)",
        JOptionPane.INFORMATION_MESSAGE
    );

    try {
      Thread.sleep(2000);
    } catch (InterruptedException ex) {
    }

    DropboxClient dropboxClient = DropboxClient.getInstance();
    dropboxClient = null;
    loadAccountInfoToForm();
  }

  public void mostrarFormulario() {
    vista.setVisible(true);
  }
}
