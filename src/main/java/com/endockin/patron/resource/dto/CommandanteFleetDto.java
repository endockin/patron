package com.endockin.patron.resource.dto;

public class CommandanteFleetDto {

    private String id;
    private String image;
    private Integer instanceNumber;
    private Double cpu;
    private Double memory;

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

    @Override
    public String toString() {
        return "CommandanteFleetDto{" + "id=" + id + ", command=" + image + ", instanceNumber=" + instanceNumber + ", cpu=" + cpu + ", memory=" + memory + '}';
    }

}
