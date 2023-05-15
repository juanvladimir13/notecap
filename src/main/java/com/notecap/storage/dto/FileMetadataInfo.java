package com.notecap.storage.dto;

/**
 * @author Juan Vladimir
 * @see <a href="https://github.com/juanvladimir13">github</a>
 */

public class FileMetadataInfo {
  public final String id;
  public final String name;
  public final String rev;
  public final String pathLower;
  public final String pathDisplay;

  public FileMetadataInfo(String id, String name, String rev, String pathLower, String pathDisplay) {
    this.id = id;
    this.name = name;
    this.rev = rev;
    this.pathLower = pathLower;
    this.pathDisplay = pathDisplay;
  }
}
