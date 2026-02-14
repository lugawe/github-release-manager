package de.lugawe.grm.controller.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonArchiveAsset {

    @JsonProperty("archive_asset_name")
    private String name;

    public JsonArchiveAsset() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
