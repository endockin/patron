package com.endockin.patron.resource.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;

public class SirenaFleetDto {

    @JsonProperty("blueprintName")
    private String blueprintName;

    @JsonProperty("name")
    private String name;

    @JsonProperty("numberOfShips")
    private int numberOfShips;

    @JsonProperty("memoryPerShip")
    private int memoryPerShip;

    @JsonProperty("cpuPerShip")
    private int cpuPerShip;

    @JsonProperty("diskPerShip")
    private int diskPerShip;

    @JsonProperty("urls")
    private List<String> urls;

    @JsonProperty("deployed")
    private boolean deployed;

    @JsonProperty("status")
    private String status;

    @JsonProperty("statusSince")
    private Date statusSince;

    public String getBlueprintName() {
        return blueprintName;
    }

    public void setBlueprintName(String blueprintName) {
        this.blueprintName = blueprintName;
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

    public int getDiskPerShip() {
        return diskPerShip;
    }

    public void setDiskPerShip(int diskPerShip) {
        this.diskPerShip = diskPerShip;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    public boolean isDeployed() {
        return deployed;
    }

    public void setDeployed(boolean deployed) {
        this.deployed = deployed;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStatusSince() {
        return statusSince;
    }

    public void setStatusSince(Date statusSince) {
        this.statusSince = statusSince;
    }

    @Override
    public String toString() {
        return "SirenaFleetDto{" + "blueprintName=" + blueprintName + ", name=" + name + ", numberOfShips=" + numberOfShips + ", memoryPerShip=" + memoryPerShip + ", cpuPerShip=" + cpuPerShip + ", diskPerShip=" + diskPerShip + ", urls=" + urls + ", deployed=" + deployed + ", status=" + status + ", statusSince=" + statusSince + '}';
    }

}
