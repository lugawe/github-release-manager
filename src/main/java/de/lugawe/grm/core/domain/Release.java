package de.lugawe.grm.core.domain;

import java.util.List;

public final class Release {

    public static final String LATEST = "latest";

    private String name;
    private List<Asset> assets;

    public Release() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Asset> getAssets() {
        return assets;
    }

    public void setAssets(List<Asset> assets) {
        this.assets = assets;
    }
}
