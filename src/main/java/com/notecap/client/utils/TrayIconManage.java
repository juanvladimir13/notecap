package com.notecap.client.utils;

import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.MouseListener;

/**
 *
 * @author juanvladimir13<juanvladimir13@gmail.com>
 * @see https://github.com/juanvladimir13
 */
public class TrayIconManage {
  private TrayIcon trayIcon;
  private PopupMenu popup;
  static Image image = Toolkit.getDefaultToolkit().getImage("resources/icon.png");

  public TrayIconManage() {
    this.popup = new PopupMenu();
  }

  public void addMenuItem(MenuItem menuItem) {
    popup.add(menuItem);
  }

  public void addMoseListener(MouseListener mouseListener) {
    trayIcon.addMouseListener(mouseListener);
  }

  public void pack(String title){
    this.trayIcon = new TrayIcon(image, title, popup);
    this.trayIcon.setImageAutoSize(true);
  }

  public void MensajeTrayIcon(String caption,String texto, TrayIcon.MessageType tipo) {
    trayIcon.displayMessage(caption, texto, tipo);
  }

  public TrayIcon getTrayIcon() {
    return trayIcon;
  }
}