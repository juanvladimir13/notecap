package com.notecap.storage;

/**
 * @author juanvladimir13 <juanvladimir13@gmail.com>
 * @see https://github.com/juanvladimir13
 */
public class ManageBrowser {
  private static boolean executeCommand(String command, String url) {
    try {
      Runtime.getRuntime().exec(command + url);
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  public static boolean openUrlInBrowserDefault(String url) {
    boolean proccessed = false;
    String osName = System.getProperty("os.name");
    if (osName.contains("Windows")) {
      proccessed = executeCommand("rundll32 url.dll,FileProtocolHandler ", url);
    } else if (osName.contains("Linux")) {
      proccessed = executeCommand("x-www-browser ", url); //sensible-browser
    } else if (osName.contains("Mac OS X")) {
      proccessed = executeCommand("open ", url);
    }
    return proccessed;
  }
}
