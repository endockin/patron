package com.endockin.patron.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.tomcat.util.codec.binary.Base64;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonIgnore
  private long id;

  @JsonProperty("password")
  @Column(name = "_password", length = 1024)
  private String password;

  @JsonProperty("firstName")
  private String firstName;

  @JsonProperty("lastName")
  private String lastName;

  @JsonProperty("email")
  private String email;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    MessageDigest digest;
    try {
      digest = MessageDigest.getInstance("SHA-256");
      byte[] digested = digest.digest(password.getBytes());
      this.password = Base64.encodeBase64String(digested);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public String toString() {
    return "User{" + "id=" + id + ", password=" + password + ", firstName=" + firstName
        + ", lastName=" + lastName + ", email=" + email + '}';
  }

}
