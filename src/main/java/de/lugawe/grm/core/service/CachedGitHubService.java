package de.lugawe.grm.core.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import de.lugawe.grm.core.domain.ArchiveAsset;
import de.lugawe.grm.core.domain.Asset;
import de.lugawe.grm.core.domain.Release;

public class CachedGitHubService implements GitHubService {

    private final GitHubService gitHubService;
    private final CacheService cacheService;

    public CachedGitHubService(GitHubService gitHubService, CacheService cacheService) {
        this.gitHubService = gitHubService;
        this.cacheService = cacheService;
    }

    @Override
    public Optional<Release> getRelease(String repository, String tagName) throws Exception {

        Optional<Release> optionalRelease = cacheService.getReleaseByTagName(repository, tagName);

        if (optionalRelease.isEmpty()) {
            Optional<Release> release = gitHubService.getRelease(repository, tagName);
            release.ifPresent(value -> cacheService.put(repository, value));
            return release;
        }

        return optionalRelease;
    }

    @Override
    public List<Asset> getAssets(String repository, String tagName) throws Exception {

        return getRelease(repository, tagName).map(Release::getAssets).orElseGet(Collections::emptyList);
    }

    @Override
    public Optional<Asset> getAsset(String repository, String tagName, String assetName) throws Exception {

        return getAssets(repository, tagName).stream()
                .filter(asset -> assetName.equals(asset.getName()))
                .findFirst();
    }

    @Override
    public List<ArchiveAsset> getArchiveAssets(String repository, String tagName, String assetName) throws Exception {

        return getAsset(repository, tagName, assetName)
                .map(Asset::getArchiveAssets)
                .orElseGet(Collections::emptyList);
    }

    @Override
    public Optional<ArchiveAsset> getArchiveAsset(String repository, String tagName, String assetName, String path)
            throws Exception {

        return getArchiveAssets(repository, tagName, assetName).stream()
                .filter(archiveAsset -> path.equals(archiveAsset.getPath()))
                .findFirst();
    }
}
