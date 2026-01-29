package de.lugawe.grm.core.domain;

import java.util.List;

public final class Release {

    private String version;
    private List<Asset> assets;

    public Release() {}

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<Asset> getAssets() {
        return assets;
    }

    public void setAssets(List<Asset> assets) {
        this.assets = assets;
    }
}
