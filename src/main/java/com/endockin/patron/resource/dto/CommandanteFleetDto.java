package com.endockin.patron.resource.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommandanteFleetDto {

  @JsonProperty("id")
  private String id;

  @JsonProperty("image")
  private String image;

  @JsonProperty("instanceNumber")
  private Integer instanceNumber;

  @JsonProperty("cpu")
  private Double cpu;

  @JsonProperty("memory")
  private Double memory;

  @JsonProperty("ports")
  private List<String> ports;

  @JsonProperty("ships")
  public List<CommandanteShipDto> ships;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public Integer getInstanceNumber() {
    return instanceNumber;
  }

  public void setInstanceNumber(Integer instanceNumber) {
    this.instanceNumber = instanceNumber;
  }

  public Double getCpu() {
    return cpu;
  }

  public void setCpu(Double cpu) {
    this.cpu = cpu;
  }

  public Double getMemory() {
    return memory;
  }

  public void setMemory(Double memory) {
    this.memory = memory;
  }

  public List<String> getPorts() {
    return ports;
  }

  public void setPorts(List<String> ports) {
    this.ports = ports;
  }

  public List<CommandanteShipDto> getShips() {
    return ships;
  }

  public void setShips(List<CommandanteShipDto> ships) {
    this.ships = ships;
  }

  @Override
  public String toString() {
    return "CommandanteFleetDto{" + "id=" + id + ", image=" + image + ", instanceNumber="
        + instanceNumber + ", cpu=" + cpu + ", memory=" + memory + ", ports=" + ports + ", ships="
        + ships + '}';
  }

}
