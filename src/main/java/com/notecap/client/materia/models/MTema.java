package com.notecap.client.materia.models;

import com.notecap.client.utils.Database;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.notecap.client.cloud.models.MCarpeta;

public class MTema {

  private int id;
  private String codigo;
  private String titulo;
  private String fecha;
  private int periodoAcademico_id;
  private String carpeta_id;

  public MTema() {
    this.id = 0;
    this.codigo = "";
    this.titulo = "";
    this.fecha = "";
    this.periodoAcademico_id = 0;
    this.carpeta_id = "";
  }

  public void cargarDatos(Map<String, String> datos) {
    this.id = Integer.parseInt(datos.getOrDefault("id", "0"));
    this.codigo = datos.getOrDefault("codigo", "");
    this.titulo = datos.getOrDefault("titulo", "");
    this.fecha = datos.getOrDefault("fecha", "");
    this.periodoAcademico_id = Integer.parseInt(datos.getOrDefault("periodo_academico_id", "0"));
    this.carpeta_id = datos.getOrDefault("carpeta_id", "");
  }

  public boolean guardar() {
    String sqlInsert = "insert into tema (codigo, titulo, fecha, periodo_academico_id, carpeta_id) "
            + "values (?,?,?,?,?);";

    String sqlUpdate = "update tema "
            + "set codigo=?, titulo=?, fecha=?, periodo_academico_id=?, carpeta_id=? "
            + "where id=?;";

    String sql = id != 0 ? sqlUpdate : sqlInsert;

    try {
      PreparedStatement statement = Database.getInstance().getConnection().prepareStatement(sql);
      statement.setString(1, codigo);
      statement.setString(2, titulo);
      statement.setString(3, fecha);
      statement.setInt(4, periodoAcademico_id);
      statement.setString(5, carpeta_id);

      if (id != 0) {
        statement.setInt(6, id);
      }

      return Database.getInstance().executeSQL(statement);
    } catch (SQLException e) {
      return false;
    }
  }

  public boolean eliminar(String id) {
    String sql = "delete from tema where id=?;";
    return Database.getInstance().delete(sql, id);
  }

  public Map<String, String> buscar(String columnName, Object columnValue) {
    String sql = "select * from tema where %s='%s' limit 1;";
    sql = String.format(sql, columnName, columnValue);

    List<Map<String, String>> resultado = Database.getInstance().executeSQLResultList(sql);
    return !resultado.isEmpty() ? resultado.get(0) : null;
  }

  public List<Map<String, String>> listado() {
    String sql = "select * from tema order by 1;";
    return Database.getInstance().executeSQLResultList(sql);
  }

  private String fullname(Map<String, String> row) {
    if (row == null) {
      return "";
    }
    return row.get("codigo") + " " + row.get("titulo");
  }

  public Map<String, String> comboBox() {
    Map<String, String> tema = new LinkedHashMap<>();

    String sql = "select * from tema order by 3 ASC;";
    List<Map<String, String>> rows = Database.getInstance().executeSQLResultList(sql);

    for (int i = 0; i < rows.size(); i++) {
      Map<String, String> row = rows.get(i);
      tema.put(row.get("id"), fullname(row));
    }
    return tema;
  }

  public String getCarpeta(String id) {
    Map<String, String> row = buscar("id", id);
    if (row == null) {
      return null;
    }

    MCarpeta carpeta = new MCarpeta();
    row = carpeta.buscar("id", row.get("carpeta_id"));
    if (row == null) {
      return null;
    }

    return row.get("path_display");
  }
}
