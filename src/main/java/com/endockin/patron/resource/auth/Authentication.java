package com.endockin.patron.resource.auth;

import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Authentication {

  @JsonProperty("key")
  private String key;

  @JsonProperty("generatedAt")
  private Date generatedAt;

  @JsonProperty("validUntil")
  private Date validUntil;

  @JsonIgnore
  private String salt;

  @JsonIgnore
  private long userId;

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public Date getGeneratedAt() {
    return generatedAt;
  }

  public void setGeneratedAt(Date generatedAt) {
    this.generatedAt = generatedAt;
  }

  public Date getValidUntil() {
    return validUntil;
  }

  public void setValidUntil(Date validUntil) {
    this.validUntil = validUntil;
  }

  public String getSalt() {
    return salt;
  }

  public void setSalt(String salt) {
    this.salt = salt;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  @Override
  public int hashCode() {
    int hash = 5;
    hash = 17 * hash + Objects.hashCode(this.key);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Authentication other = (Authentication) obj;
    if (!Objects.equals(this.key, other.key)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "Authentication{" + "key=" + key + ", generatedAt=" + generatedAt + ", validUntil="
        + validUntil + ", salt=" + salt + ", userId=" + userId + '}';
  }

}
