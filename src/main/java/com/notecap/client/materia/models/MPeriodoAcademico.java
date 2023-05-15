package com.notecap.client.materia.models;

import com.notecap.client.utils.Database;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MPeriodoAcademico {

  private int id;
  private String fecha;
  private String nombre;

  private String materia_id;
  private String docente_id;
  private String carpeta_id;

  public MPeriodoAcademico() {
    this.id = 0;
    this.nombre = "";
    this.fecha = "";
    this.materia_id = "";
    this.docente_id = "";
    this.carpeta_id = "";
  }

  public void cargarDatos(Map<String, String> datos) {
    this.id = Integer.parseInt(datos.getOrDefault("id", "0"));
    this.fecha = datos.getOrDefault("fecha", "");
    this.nombre = datos.getOrDefault("nombre", "");
    this.materia_id = datos.getOrDefault("materia_id", "");
    this.docente_id = datos.getOrDefault("docente_id", "");
    this.carpeta_id = datos.getOrDefault("carpeta_id", "");
  }

  public boolean guardar() {
    String sqlInsert = "insert into periodo_academico (nombre, fecha, materia_id, docente_id, carpeta_id) " + "values (?,?,?,?,?);";

    String sqlUpdate = "update periodo_academico "
        + "set nombre=?, nivel=?, materia_id=?, docente_id=?, carpeta_id=? " + "where id=?;";

    String sql = id != 0 ? sqlUpdate : sqlInsert;

    try {
      PreparedStatement statement = Database.getInstance().getConnection().prepareStatement(sql);
      statement.setString(1, nombre);
      statement.setString(2, fecha);
      statement.setString(3, materia_id);
      statement.setString(4, docente_id);
      statement.setString(5, carpeta_id);

      if (id != 0) {
        statement.setInt(6, id);
      }

      return Database.getInstance().executeSQL(statement);
    } catch (SQLException e) {
      System.out.println(e);
      return false;
    }
  }

  public boolean eliminar(String id) {
    String sql = "delete from periodo_academico where id=?;";
    return Database.getInstance().delete(sql, id);
  }

  public Map<String, String> buscar(String columnName, Object columnValue) {
    String sql = "select * from periodo_academico where %s='%s' limit 1;";
    sql = String.format(sql, columnName, columnValue);

    List<Map<String, String>> resultado = Database.getInstance().executeSQLResultList(sql);
    return !resultado.isEmpty() ? resultado.get(0) : null;
  }

  public List<Map<String, String>> listado() {
    String sql = "select * from periodo_academico order by 2;";
    return Database.getInstance().executeSQLResultList(sql);
  }

  private String fullname(Map<String, String> row) {
    if (row == null) {
      return "";
    }
    return row.get("periodo") + " " + row.get("nombre");
  }

  public Map<String, String> comboBox() {
    Map<String, String> periodoAcademico = new LinkedHashMap<>();

    String sql = "select * from periodo_academico order by 3;";
    List<Map<String, String>> rows = Database.getInstance().executeSQLResultList(sql);

    for (int i = 0; i < rows.size(); i++) {
      Map<String, String> row = rows.get(i);
      periodoAcademico.put(row.get("id"), fullname(row));
    }
    return periodoAcademico;
  }
}
