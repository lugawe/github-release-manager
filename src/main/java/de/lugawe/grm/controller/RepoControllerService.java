package de.lugawe.grm.controller;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import de.lugawe.grm.controller.json.JsonArchiveAsset;
import de.lugawe.grm.controller.json.JsonAsset;
import de.lugawe.grm.controller.json.JsonConverter;
import de.lugawe.grm.controller.json.JsonRelease;
import de.lugawe.grm.core.domain.ArchiveAsset;
import de.lugawe.grm.core.domain.Asset;
import de.lugawe.grm.core.domain.Release;
import de.lugawe.grm.core.exception.GRMException;
import de.lugawe.grm.core.service.GitHubService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class RepoControllerService {

    private static final Logger log = LoggerFactory.getLogger(RepoControllerService.class);

    @Inject
    private JsonConverter jsonConverter;

    @Inject
    private GitHubService gitHubService;

    public RepoControllerService() {}

    public JsonRelease getRelease(String repository, String tagName) {

        if (tagName == null || tagName.isEmpty()) {
            tagName = Release.LATEST;
        }

        log.info("Get release '{}' from '{}'", tagName, repository);

        Release release;
        try {
            release = gitHubService.getRelease(repository, tagName);
        } catch (Exception e) {
            throw new GRMException("Could not get release '" + tagName + "' from '" + repository + "'", e);
        }

        return jsonConverter.toJsonRelease(release);
    }

    public List<JsonAsset> getAssets(String repository, String tagName) {

        if (tagName == null || tagName.isEmpty()) {
            tagName = Release.LATEST;
        }

        log.info("Get assets from release '{}' in '{}'", tagName, repository);

        List<Asset> assets;
        try {
            assets = gitHubService.getAssets(repository, tagName);
        } catch (Exception e) {
            throw new GRMException("Could not get assets from release '" + tagName + "' in '" + repository + "'", e);
        }

        return assets.stream().map(jsonConverter::toJsonAsset).toList();
    }

    public JsonAsset getAsset(String repository, String tagName, String assetName) {

        if (tagName == null || tagName.isEmpty()) {
            tagName = Release.LATEST;
        }

        log.info("Get asset '{}' from release '{}' in '{}'", assetName, tagName, repository);

        Asset asset;
        try {
            asset = gitHubService.getAsset(repository, tagName, assetName);
        } catch (Exception e) {
            throw new GRMException(
                    "Could not get asset '" + assetName + "' from release '" + tagName + "' in '" + repository + "'",
                    e);
        }

        return jsonConverter.toJsonAsset(asset);
    }

    public List<JsonArchiveAsset> getArchiveAssets(String repository, String tagName, String assetName) {

        if (tagName == null || tagName.isEmpty()) {
            tagName = Release.LATEST;
        }

        log.info("Get archive assets from asset '{}' from release '{}' in '{}'", assetName, tagName, repository);

        List<ArchiveAsset> archiveAssets;
        try {
            archiveAssets = gitHubService.getArchiveAssets(repository, tagName, assetName);
        } catch (Exception e) {
            throw new GRMException(
                    "Could not extract archive asset '" + assetName + "' from release '" + tagName + "' in '"
                            + repository + "'",
                    e);
        }

        return archiveAssets.stream().map(jsonConverter::toJsonArchiveAsset).toList();
    }

    public JsonArchiveAsset getArchiveAsset(
            String repository, String tagName, String assetName, String archiveAssetName) {

        if (tagName == null || tagName.isEmpty()) {
            tagName = Release.LATEST;
        }

        log.info(
                "Get archive asset '{}' from asset '{}' from release '{}' in '{}'",
                archiveAssetName,
                assetName,
                tagName,
                repository);

        ArchiveAsset archiveAsset;
        try {
            archiveAsset = gitHubService.getArchiveAsset(repository, tagName, assetName, archiveAssetName);
        } catch (Exception e) {
            throw new GRMException(
                    "Could not extract archive asset '" + assetName + "' from release '" + tagName + "' in '"
                            + repository + "'",
                    e);
        }

        return jsonConverter.toJsonArchiveAsset(archiveAsset);
    }
}
