package com.endockin.patron.model;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Fleet {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonIgnore
  private long id;

  @ManyToOne
  private Blueprint blueprint;
  private String name;
  private int numberOfShips;
  private int memoryPerShip;
  private int cpuPerShip;
  private int diskSpace;
  private Date stagedAt;
  private Date startedAt; 


  @ManyToOne
  private User user;

  /**
   * Comma separated list of urls
   */
  @Column(length = 1024)
  private String urls;

  public Date getStagedAt() {
      return stagedAt;
  }
  
  public void setStagedAt(Date stagedAt) {
      this.stagedAt = stagedAt;
  }
  
  public Date getStartedAt() {
      return startedAt;
  }
  
  public void setStartedAt(Date startedAt) {
      this.startedAt = startedAt;
  }

  public User getUser() {
    return this.user;
  }

  public void setUser(User myUser) {
    this.user = myUser;
  }

  public Blueprint getBlueprint() {
    return blueprint;
  }

  public void setBlueprint(Blueprint blueprint) {
    this.blueprint = blueprint;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getNumberOfShips() {
    return numberOfShips;
  }

  public void setNumberOfShips(int numberOfShips) {
    this.numberOfShips = numberOfShips;
  }

  public int getMemoryPerShip() {
    return memoryPerShip;
  }

  public void setMemoryPerShip(int memoryPerShip) {
    this.memoryPerShip = memoryPerShip;
  }

  public int getCpuPerShip() {
    return cpuPerShip;
  }

  public void setCpuPerShip(int cpuPerShip) {
    this.cpuPerShip = cpuPerShip;
  }

  public int getDiskSpace() {
    return diskSpace;
  }

  public void setDiskSpace(int diskSpace) {
    this.diskSpace = diskSpace;
  }

  public List<String> getUrls() {
    return Arrays.asList(urls.split(","));
  }

  public void setUrls(String urls) {
    this.urls = urls;
  }

  @Override
  public String toString() {
    return "Fleet{" + "blueprint=" + blueprint + ", name=" + name + ", numberOfShips="
        + numberOfShips + ", memoryPerShip=" + memoryPerShip + ", cpuPerShip=" + cpuPerShip
        + ", diskSpace=" + diskSpace + ", urls=" + urls + ", stagedAt=" + stagedAt + ", startedAt=" + startedAt + '}';
  }

}
