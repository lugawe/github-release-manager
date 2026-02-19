package de.lugawe.grm.core.service;

import java.util.List;
import java.util.Optional;

import de.lugawe.grm.core.domain.ArchiveAsset;
import de.lugawe.grm.core.domain.Asset;
import de.lugawe.grm.core.domain.Release;

public interface GitHubService {

    Optional<Release> getRelease(String repository, String tagName) throws Exception;

    List<Asset> getAssets(String repository, String tagName) throws Exception;

    Optional<Asset> getAsset(String repository, String tagName, String assetName) throws Exception;

    List<ArchiveAsset> getArchiveAssets(String repository, String tagName, String assetName) throws Exception;

    Optional<ArchiveAsset> getArchiveAsset(String repository, String tagName, String assetName, String path)
            throws Exception;
}
