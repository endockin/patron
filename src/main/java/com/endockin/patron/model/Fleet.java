package com.endockin.patron.model;

import java.util.List;

public class Fleet {

  private Blueprint blueprint;
  private String name;
  private int numberOfShips;
  private int memoryPerShip;
  private int cpuPerShip;
  private int diskSpace;
  private List<String> urls;

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
    return urls;
  }

  public void setUrls(List<String> urls) {
    this.urls = urls;
  }

  @Override
  public String toString() {
    return "Fleet{" + "blueprint=" + blueprint + ", name=" + name + ", numberOfShips="
        + numberOfShips + ", memoryPerShip=" + memoryPerShip + ", cpuPerShip=" + cpuPerShip
        + ", diskSpace=" + diskSpace + ", urls=" + urls + '}';
  }

}
