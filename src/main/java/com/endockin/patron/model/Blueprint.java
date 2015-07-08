package com.endockin.patron.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Blueprint {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonIgnore
  private long id;

  @JsonProperty("imageName")
  private String imageName;

  @JsonProperty("name")
  private String name;

  @JsonProperty("description")
  @Column(length = 1024)
  private String description;

  @JsonProperty("logo")
  private String logo;

  @JsonIgnore
  /**
   * Comma separated list of ports
   */
  private String ports;

  public String getImageName() {
    return this.imageName;
  }

  public void setImageName(String image) {
    this.imageName = image;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getLogo() {
    return logo;
  }

  public void setLogo(String logo) {
    this.logo = logo;
  }

  public List<String> getPorts() {
    if (ports != null) {
      String[] portsArray = ports.split(",");
      return Arrays.asList(portsArray);
    }

    return Collections.emptyList();
  }

  public void setPorts(String ports) {
    this.ports = ports;
  }

  @Override
  public String toString() {
    return "Blueprint{" + "id=" + id + ", name=" + name + ", description=" + description
        + ", logo=" + logo + ", ports=" + ports + '}';
  }

}
