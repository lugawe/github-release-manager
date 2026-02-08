package de.lugawe.grm.controller.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonAsset {

    @JsonProperty("asset_name")
    private String name;

    public JsonAsset() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
