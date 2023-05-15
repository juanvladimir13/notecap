package com.notecap.client.cloud.models;

import com.notecap.client.utils.Database;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MCarpeta {

  private String id;
  private String name;
  private String path_lower;
  private String path_display;

  public MCarpeta() {
    this.id = "";
    this.name = "";
    this.path_lower = "";
    this.path_display = "";
  }

  public void cargarDatos(Map<String, String> datos) {
    this.id = datos.getOrDefault("id", "");
    this.name = datos.getOrDefault("name", "");
    this.path_lower = datos.getOrDefault("path_lower", "");
    this.path_display = datos.getOrDefault("path_display", "");
  }

  public boolean guardar() {
    String sql = "insert into carpeta (id, name, path_lower, path_display) "
            + "values (?,?,?,?);";

    try {
      PreparedStatement statement = Database.getInstance().getConnection().prepareStatement(sql);
      statement.setString(1, id);
      statement.setString(2, name);
      statement.setString(3, path_lower);
      statement.setString(4, path_display);

      return Database.getInstance().executeSQL(statement);
    } catch (SQLException e) {
      return false;
    }
  }
  
  public boolean eliminar(String id){
    String sql = "delete from carpeta where id=?;";
    return Database.getInstance().delete(sql, id);
  }

  public Map<String, String> buscar(String columnName, Object columnValue) {
    String sql = "select * from carpeta where %s='%s' limit 1;";
    sql = String.format(sql, columnName, columnValue);

    List<Map<String, String>> resultado = Database.getInstance().executeSQLResultList(sql);
    return !resultado.isEmpty() ? resultado.get(0) : null;
  }

  public List<Map<String, String>> listado() {
    String sql = "select * from carpeta order by 2;";
    return Database.getInstance().executeSQLResultList(sql);
  }

  public Map<String, String> comboBox() {
    Map<String, String> carpeta = new LinkedHashMap<>();

    String sql = "select * from carpeta order by 2;";
    List<Map<String, String>> rows = Database.getInstance().executeSQLResultList(sql);

    for (int i = 0; i < rows.size(); i++) {
      Map<String, String> row = rows.get(i);
      if (row != null) {
        carpeta.put(row.get("id"), row.get("path_display"));
      }
    }
    return carpeta;
  }
}
