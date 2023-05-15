package com.notecap.client.utils;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 *
 * @author juanvladimir13<juanvladimir13@gmail.com>
 * @see https://github.com/juanvladimir13
 */
public class ComboBoxManager extends AbstractListModel<ComboBoxItem> implements ComboBoxModel<ComboBoxItem> {

  private ComboBoxItem model;
  private Map<String, ComboBoxItem> dataList;

  public ComboBoxManager() {
    this.model = null;
    this.dataList = new LinkedHashMap<>();
  }

  public void setDataList(Map<String, String> dataList) {
    this.dataList.clear();

    for (Map.Entry<String, String> entry : dataList.entrySet()) {
      this.dataList.put(entry.getKey(), new ComboBoxItem(entry.getKey(), entry.getValue()));
    }
  }

  @Override
  public int getSize() {
    return dataList.size();
  }

  @Override
  public ComboBoxItem getSelectedItem() {
    return model;
  }

  @Override
  public ComboBoxItem getElementAt(int arg0) {
    if (arg0 < 0 || arg0 > dataList.size()) {
      return null;
    }

    int i = 0;

    for (Map.Entry<String, ComboBoxItem> entry : dataList.entrySet()) {
      if (i == arg0) {
        return entry.getValue();
      }
      i++;
    }
    return null;
  }

  @Override
  public void setSelectedItem(Object key) {
    model = dataList.get(((ComboBoxItem) key).getId());
    fireContentsChanged(this, 0, 0);
  }
}