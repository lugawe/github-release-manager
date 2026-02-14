package de.lugawe.grm.controller.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonArchiveAsset {

    @JsonProperty("archive_asset_path")
    private String path;

    @JsonProperty("archive_asset_name")
    private String name;

    public JsonArchiveAsset() {}

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
