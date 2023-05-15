package com.notecap.storage;

import com.dropbox.core.v2.users.FullAccount;

/**
 * @author juanvladimir13<juanvladimir13 @ gmail.com>
 * @see https://github.com/juanvladimir13
 */
public class AccountInfo {

  private String id;
  private String displayName;
  private String email;
  private String locale;
  private String country;

  public AccountInfo() {
    init();
  }

  private void init() {
    this.id = "";
    this.displayName = "";
    this.email = "";
    this.locale = "";
    this.country = "";
  }

  public boolean loadAccountInfo() {
    FullAccount account = DropboxClient.getInstance().getInfoAccount();
    if (account == null) {
      init();
      return false;
    }

    id = account.getAccountId();
    displayName = account.getName().getDisplayName();
    email = account.getEmail();
    locale = account.getLocale();
    country = account.getCountry();

    return true;
  }

  public String getId() {
    return id;
  }

  public String getDisplayName() {
    return displayName;
  }

  public String getEmail() {
    return email;
  }

  public String getLocale() {
    return locale;
  }

  public String getCountry() {
    return country;
  }

  @Override
  public String toString() {
    return displayName + " | " + email + " | " + locale + " | " + country;
  }
}
