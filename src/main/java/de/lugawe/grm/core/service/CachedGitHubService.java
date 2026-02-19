package de.lugawe.grm.core.service;

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
    public Release getRelease(String repository, String tagName) throws Exception {

        Optional<Release> optionalRelease = cacheService.getReleaseByTagName(repository, tagName);

        if (optionalRelease.isEmpty()) {
            Release release = gitHubService.getRelease(repository, tagName);
            cacheService.put(repository, release);
            return release;
        }

        return optionalRelease.get();
    }

    @Override
    public List<Asset> getAssets(String repository, String tagName) throws Exception {

        return getRelease(repository, tagName).getAssets();
    }

    @Override
    public Asset getAsset(String repository, String tagName, String assetName) throws Exception {

        return getAssets(repository, tagName).stream()
                .filter(asset -> assetName.equals(asset.getName()))
                .findFirst()
                .orElseThrow();
    }

    @Override
    public List<ArchiveAsset> getArchiveAssets(String repository, String tagName, String assetName) throws Exception {

        return getAsset(repository, tagName, assetName).getArchiveAssets();
    }

    @Override
    public ArchiveAsset getArchiveAsset(String repository, String tagName, String assetName, String path)
            throws Exception {

        return getArchiveAssets(repository, tagName, assetName).stream()
                .filter(archiveAsset -> path.equals(archiveAsset.getPath()))
                .findFirst()
                .orElseThrow();
    }
}
