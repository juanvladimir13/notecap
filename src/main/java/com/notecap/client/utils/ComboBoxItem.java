package com.notecap.client.utils;

/**
 *
 * @author juanvladimir13<juanvladimir13@gmail.com>
 * @see https://github.com/juanvladimir13
 */
public class ComboBoxItem {
  private String id;
  private String value;

  public ComboBoxItem(String id, String value) {
    this.id = id;
    this.value = value;
  }

  public String getId() {
    return id;
  }

  @Override
  public String toString() {
    return value;
  }
}
