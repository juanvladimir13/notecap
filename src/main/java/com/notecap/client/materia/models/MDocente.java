package com.notecap.client.materia.models;

import com.notecap.client.utils.Database;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MDocente {

  private int id;
  private String nombres;
  private String apellidos;
  private String celular;
  private String email;

  public MDocente() {
    this.id = 0;
    this.nombres = "";
    this.apellidos = "";
    this.celular = "";
    this.email = "";
  }

  public void cargarDatos(Map<String, String> datos) {
    this.id = Integer.parseInt(datos.getOrDefault("id", "0"));
    this.nombres = datos.getOrDefault("nombres", "");
    this.apellidos = datos.getOrDefault("apellidos", "");
    this.celular = datos.getOrDefault("celular", "");
    this.email = datos.getOrDefault("email", "");
  }

  public boolean guardar() {
    String sqlInsert = "insert into docente (nombres, apellidos, celular, email) "
            + "values (?,?,?,?);";

    String sqlUpdate = "update docente "
            + "set nombres=?, apellidos=?, celular=?, email=? "
            + "where id=?;";

    String sql = id != 0 ? sqlUpdate : sqlInsert;

    try {
      PreparedStatement statement = Database.getInstance().getConnection().prepareStatement(sql);
      statement.setString(1, nombres);
      statement.setString(2, apellidos);
      statement.setString(3, celular);
      statement.setString(4, email);

      if (id != 0) {
        statement.setInt(5, id);
      }

      return Database.getInstance().executeSQL(statement);
    } catch (SQLException e) {
      return false;
    }
  }

  public boolean eliminar(String id) {
    String sql = "delete from docente where id=?;";
    return Database.getInstance().delete(sql, id);
  }

  public Map<String, String> buscar(String columnName, Object columnValue) {
    String sql = "select * from docente where %s='%s' limit 1;";
    sql = String.format(sql, columnName, columnValue);

    List<Map<String, String>> resultado = Database.getInstance().executeSQLResultList(sql);
    return !resultado.isEmpty() ? resultado.get(0) : null;
  }

  public List<Map<String, String>> listado() {
    String sql = "select * from docente order by 3;";
    return Database.getInstance().executeSQLResultList(sql);
  }

  private String fullname(Map<String, String> row) {
    if (row == null) {
      return "";
    }
    return row.get("nombres") + " " + row.get("apellidos");
  }

  public Map<String, String> comboBox() {
    Map<String, String> docente = new LinkedHashMap<>();

    String sql = "select * from docente order by 3;";
    List<Map<String, String>> rows = Database.getInstance().executeSQLResultList(sql);

    for (int i = 0; i < rows.size(); i++) {
      Map<String, String> row = rows.get(i);
      docente.put(row.get("id"), fullname(row));
    }
    return docente;
  }
}
