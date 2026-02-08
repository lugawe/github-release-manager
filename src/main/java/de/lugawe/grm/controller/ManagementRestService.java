package de.lugawe.grm.controller;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import de.lugawe.grm.controller.json.JsonConverter;
import de.lugawe.grm.controller.json.JsonRelease;
import de.lugawe.grm.core.domain.Release;
import de.lugawe.grm.core.exception.GRMException;
import de.lugawe.grm.core.service.GitHubService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class ManagementRestService {

    private static final Logger log = LoggerFactory.getLogger(ManagementRestService.class);

    @Inject
    private JsonConverter jsonConverter;

    @Inject
    private GitHubService gitHubService;

    public ManagementRestService() {}

    public List<JsonRelease> getReleases(String repository, int page, int size) {

        log.info("Get releases from '{}', page {}, size {}", repository, page, size);

        List<Release> releases;
        try {
            releases = gitHubService.getReleases(repository, page, size);
        } catch (Exception e) {
            throw new GRMException("Could not get releases from '" + repository + "'", e);
        }

        return releases.stream().map(jsonConverter::toJsonRelease).toList();
    }

    public JsonRelease getRelease(String repository, String releaseName) {

        if (releaseName == null || releaseName.isEmpty()) {
            releaseName = Release.LATEST;
        }

        log.info("Get release '{}' from '{}'", releaseName, repository);

        Release release;
        try {
            release = gitHubService.getRelease(repository, releaseName);
        } catch (Exception e) {
            throw new GRMException("Could not get release '" + releaseName + "' from '" + repository + "'", e);
        }

        return jsonConverter.toJsonRelease(release);
    }
}
