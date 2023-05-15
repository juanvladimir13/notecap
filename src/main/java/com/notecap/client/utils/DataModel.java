package com.notecap.client.utils;

import java.util.List;
import java.util.Map;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
/**
 *
 * @author juanvladimir13<juanvladimir13@gmail.com>
 * @see https://github.com/juanvladimir13
 */
public class DataModel extends AbstractTableModel implements TableModel {

  private List<Map<String, String>> dataList;
  private String[] dataColumnName;
  private String[] columnName;

  public DataModel(String[] columnName, List<Map<String, String>> dataList, String[] dataColumnName) {
    this.dataColumnName = dataColumnName;
    this.columnName = columnName;
    this.dataList = dataList;
    fireTableStructureChanged();
  }

  public void setDataList(List<Map<String, String>> dataList) {
    this.dataList = dataList;
    fireTableStructureChanged();
  }

  public void addItem(Map<String, String> data) {
    dataList.add(data);
    fireTableStructureChanged();
  }

  public void deleteItem(int row) {
    if (row > -1) {
      dataList.remove(row);
      fireTableStructureChanged();
    }
  }

  public String getValueAt(int row, String col) {
    if (row < 0 || row > dataList.size()) {
      return null;
    }
    return dataList.get(row).getOrDefault(col, "");
  }

  @Override
  public int getRowCount() {
    return dataList.size();
  }

  @Override
  public int getColumnCount() {
    return dataColumnName.length;
  }

  @Override
  public Object getValueAt(int row, int col) {
    if (row > dataList.size() && col > dataColumnName.length) {
      return null;
    }
    return dataList.get(row).getOrDefault(dataColumnName[col], "");
  }

  @Override
  public String getColumnName(int columnIndex) {
    if (columnIndex < 0 || columnIndex >= getColumnCount()) {
      throw new ArrayIndexOutOfBoundsException(columnIndex);
    } else {
      return columnName[columnIndex];
    }
  }
}
