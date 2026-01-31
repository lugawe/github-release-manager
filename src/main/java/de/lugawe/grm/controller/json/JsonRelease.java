package de.lugawe.grm.controller.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonRelease {

    @JsonProperty("version")
    private String name;

    public JsonRelease() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
