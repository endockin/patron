package com.endockin.patron.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonIgnore
  private long id;

  @JsonProperty("key")
  @Column(name = "_key")
  private String key;

  @JsonProperty("name")
  private String name;

  @JsonProperty("blueprints")
  @OneToMany(cascade = CascadeType.PERSIST)
  private List<Blueprint> blueprints;

  public String getKey() {
    return this.key;
  }

  public void setKey(String theKey) {
    this.key = theKey;
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

  public List<Blueprint> getBlueprints() {
    return blueprints;
  }

  public void setBlueprints(List<Blueprint> blueprints) {
    this.blueprints = blueprints;
  }

  @Override
  public String toString() {
    return "Category{" + "id=" + id + ", name=" + name + ", blueprints=" + blueprints + '}';
  }

}
