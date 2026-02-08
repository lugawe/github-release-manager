package de.lugawe.grm.controller.json;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;

import de.lugawe.grm.core.domain.Asset;
import de.lugawe.grm.core.domain.Release;

@ApplicationScoped
public class JsonConverter {

    public JsonConverter() {}

    public JsonAsset toJsonAsset(Asset asset) {

        JsonAsset result = new JsonAsset();
        result.setName(asset.getName());

        return result;
    }

    public JsonRelease toJsonRelease(Release release) {

        JsonRelease result = new JsonRelease();
        result.setName(release.getName());

        List<Asset> assets = release.getAssets();
        if (assets != null) {
            result.setAssets(assets.stream().map(this::toJsonAsset).toList());
        }

        return result;
    }
}
