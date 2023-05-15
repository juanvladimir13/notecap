package com.notecap.client.cloud.models;

import com.notecap.client.utils.Database;
import java.sql.*;
import java.util.List;
import java.util.Map;

public class MArchivo {

  private String id;
  private String name;
  private String rev;
  private String path_lower;
  private String path_display;
  private int tema_id;

  public MArchivo() {
    this.id = "";
    this.name = "";
    this.rev = "";
    this.path_lower = "";
    this.path_display = "";
    this.tema_id = 0;
  }

  public void cargarDatos(Map<String, String> datos) {
    this.id = datos.getOrDefault("id", "");
    this.name = datos.getOrDefault("name", "");
    this.rev = datos.getOrDefault("rev", "");
    this.path_lower = datos.getOrDefault("path_lower", "");
    this.path_display = datos.getOrDefault("path_display", "");
    this.tema_id = Integer.parseInt(datos.getOrDefault("tema_id", "0"));
  }

  public boolean guardar() {
    String sql = "insert into archivo (id, name, rev, path_lower, path_display, tema_id) " + "values (?,?,?,?,?,?);";

    try {
      PreparedStatement statement = Database.getInstance().getConnection().prepareStatement(sql);
      statement.setString(1, id);
      statement.setString(2, name);
      statement.setString(3, rev);
      statement.setString(4, path_lower);
      statement.setString(5, path_display);
      statement.setInt(6, tema_id);

      return Database.getInstance().executeSQL(statement);
    } catch (SQLException e) {
      return false;
    }
  }

  public List<Map<String, String>> listado(String tema_id) {
    String sql = "select * from archivo where tema_id='%s' order by 2;";
    sql = String.format(sql, tema_id);
    return Database.getInstance().executeSQLResultList(sql);
  }
}
