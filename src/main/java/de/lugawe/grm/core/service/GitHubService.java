package de.lugawe.grm.core.service;

import java.io.InputStream;
import java.util.List;

import de.lugawe.grm.core.domain.ArchiveAsset;
import de.lugawe.grm.core.domain.Asset;
import de.lugawe.grm.core.domain.Release;

public interface GitHubService {

    Release getRelease(String repository, String tagName) throws Exception;

    List<Asset> getAssets(String repository, String tagName) throws Exception;

    Asset getAsset(String repository, String tagName, String assetName) throws Exception;

    InputStream getAssetContent(String repository, String tagName, String assetName) throws Exception;

    List<ArchiveAsset> getArchiveAssets(String repository, String tagName, String assetName) throws Exception;

    ArchiveAsset getArchiveAsset(String repository, String tagName, String assetName, String path) throws Exception;

    InputStream getArchiveAssetContent(String repository, String tagName, String assetName, String path)
            throws Exception;
}
