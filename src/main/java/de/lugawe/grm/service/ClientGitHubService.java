package de.lugawe.grm.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import de.lugawe.grm.core.domain.ArchiveAsset;
import de.lugawe.grm.core.domain.Asset;
import de.lugawe.grm.core.domain.Release;
import de.lugawe.grm.core.exception.GRMException;
import de.lugawe.grm.core.service.ArchiveService;
import de.lugawe.grm.core.service.GitHubService;
import io.quarkus.cache.CacheResult;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.kohsuke.github.GHAsset;
import org.kohsuke.github.GHRelease;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class ClientGitHubService implements GitHubService {

    private static final Logger log = LoggerFactory.getLogger(ClientGitHubService.class);

    @Inject
    private ArchiveService archiveService;

    @ConfigProperty(name = "grm.github-token")
    private String gitHubToken;

    private GitHub gitHub;

    public ClientGitHubService() {}

    @PostConstruct
    public void initialize() {
        log.info("Initializing GitHub client");
        try {
            this.gitHub = new GitHubBuilder().withOAuthToken(gitHubToken).build();
        } catch (IOException e) {
            throw new GRMException("Could not build GitHub client. Invalid credentials?", e);
        }
    }

    private Asset toAsset(GHAsset asset) {
        Asset result = new Asset();
        result.setName(asset.getName());
        result.setContentType(asset.getContentType());
        result.setUrl(asset.getBrowserDownloadUrl());
        return result;
    }

    private Release toRelease(GHRelease release) {
        Release result = new Release();
        result.setName(release.getName());
        try {
            result.setAssets(
                    release.listAssets().toList().stream().map(this::toAsset).toList());
        } catch (IOException e) {
            throw new GRMException("Could not list assets for release '" + release.getName() + "'", e);
        }
        return result;
    }

    @CacheResult(cacheName = "asset-content")
    public byte[] getAssetContent(String url) {
        log.info("Loading asset content from '{}'", url);
        try {
            HttpURLConnection connection =
                    (HttpURLConnection) URI.create(url).toURL().openConnection();
            connection.setRequestProperty("Authorization", "Bearer " + gitHubToken);
            connection.setInstanceFollowRedirects(true);
            try (InputStream inputStream = connection.getInputStream()) {
                return inputStream.readAllBytes();
            }
        } catch (Exception e) {
            throw new GRMException("Could not load asset content", e);
        }
    }

    @Override
    @CacheResult(cacheName = "release")
    public Release getRelease(String repository, String tagName) {

        log.info("Loading release '{}' from repository '{}'", tagName, repository);

        GHRepository repo;
        try {
            repo = gitHub.getRepository(repository);
            if (repo == null) {
                throw new RuntimeException("Repository not found");
            }
        } catch (IOException e) {
            throw new GRMException("Could not get repository", e);
        }

        GHRelease release;
        try {
            release = Release.LATEST.equals(tagName) ? repo.getLatestRelease() : repo.getReleaseByTagName(tagName);
            if (release == null) {
                throw new RuntimeException("Release not found");
            }
        } catch (IOException e) {
            throw new GRMException("Could not get release", e);
        }

        return toRelease(release);
    }

    @Override
    @CacheResult(cacheName = "assets")
    public List<Asset> getAssets(String repository, String tagName) {

        log.info("Loading assets for release '{}' in repository '{}'", tagName, repository);

        Release release = getRelease(repository, tagName);

        return release.getAssets();
    }

    @Override
    @CacheResult(cacheName = "asset")
    public Asset getAsset(String repository, String tagName, String assetName) {

        log.info("Loading asset '{}' from release '{}' in repository '{}'", assetName, tagName, repository);

        List<Asset> assets = getAssets(repository, tagName);

        return assets.stream()
                .filter(asset -> assetName.equals(asset.getName()))
                .findFirst()
                .orElseThrow();
    }

    @Override
    public InputStream getAssetContent(String repository, String tagName, String assetName) {

        Asset asset = getAsset(repository, tagName, assetName);

        return new ByteArrayInputStream(getAssetContent(asset.getUrl()));
    }

    @Override
    @CacheResult(cacheName = "archive-assets")
    public List<ArchiveAsset> getArchiveAssets(String repository, String tagName, String assetName) {

        log.info(
                "Loading archive assets from asset '{}' in release '{}' in repository '{}'",
                assetName,
                tagName,
                repository);

        InputStream inputStream = getAssetContent(repository, tagName, assetName);

        return archiveService.extract(inputStream);
    }

    @Override
    @CacheResult(cacheName = "archive-asset")
    public ArchiveAsset getArchiveAsset(String repository, String tagName, String assetName, String path) {

        log.info(
                "Loading archive asset '{}' from asset '{}' in release '{}' in repository '{}'",
                path,
                assetName,
                tagName,
                repository);

        List<ArchiveAsset> archiveAssets = getArchiveAssets(repository, tagName, assetName);

        return archiveAssets.stream()
                .filter(archiveAsset -> path.equals(archiveAsset.getPath()))
                .findFirst()
                .orElseThrow();
    }

    @Override
    public InputStream getArchiveAssetContent(String repository, String tagName, String assetName, String path) {

        ArchiveAsset archiveAsset = getArchiveAsset(repository, tagName, assetName, path);

        return new ByteArrayInputStream(archiveAsset.getContent());
    }
}
