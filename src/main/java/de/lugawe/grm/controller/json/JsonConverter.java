package de.lugawe.grm.controller.json;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;

import de.lugawe.grm.core.domain.ArchiveAsset;
import de.lugawe.grm.core.domain.Asset;
import de.lugawe.grm.core.domain.Release;

@ApplicationScoped
public class JsonConverter {

    public JsonConverter() {}

    public JsonArchiveAsset toJsonArchiveAsset(ArchiveAsset archiveAsset) {

        JsonArchiveAsset result = new JsonArchiveAsset();
        result.setPath(archiveAsset.getPath());
        result.setName(archiveAsset.getName());

        return result;
    }

    public JsonAsset toJsonAsset(Asset asset) {

        JsonAsset result = new JsonAsset();
        result.setName(asset.getName());
        result.setContentType(asset.getContentType());
        result.setUrl(asset.getUrl());

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
