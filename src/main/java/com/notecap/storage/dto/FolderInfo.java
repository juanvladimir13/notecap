package com.notecap.storage.dto;

/**
 * @author Juan Vladimir
 * @see <a href="https://github.com/juanvladimir13">github</a>
 */

public class FolderInfo {
  public final String id;
  public final String name;
  public final String path_lower;
  public final String path_display;

  public FolderInfo(String id, String name, String path_lower, String path_display) {
    this.id = id;
    this.name = name;
    this.path_lower = path_lower;
    this.path_display = path_display;
  }
}
