package de.lugawe.grm.core.service;

import java.util.List;

import de.lugawe.grm.core.domain.ArchiveAsset;
import de.lugawe.grm.core.domain.Asset;
import de.lugawe.grm.core.domain.Release;

public interface GitHubService {

    List<Release> getReleases(String repository, int page, int size) throws Exception;

    Release getRelease(String repository, String releaseName) throws Exception;

    default Release getLatestRelease(String repository) throws Exception {
        return getRelease(repository, Release.LATEST);
    }

    List<Asset> getAssets(String repository, String releaseName) throws Exception;

    Asset getAsset(String repository, String releaseName, String assetName) throws Exception;

    List<ArchiveAsset> getArchiveAssets(String repository, String releaseName, String assetName) throws Exception;

    ArchiveAsset getArchiveAsset(String repository, String releaseName, String assetName, String path) throws Exception;
}
