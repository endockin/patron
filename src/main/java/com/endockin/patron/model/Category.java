package com.endockin.patron.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class Category {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("blueprints")
    private List<Blueprint> blueprints;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
