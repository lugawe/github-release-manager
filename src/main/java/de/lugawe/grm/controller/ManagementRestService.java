package de.lugawe.grm.controller;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import de.lugawe.grm.controller.json.JsonConverter;
import de.lugawe.grm.controller.json.JsonRelease;
import de.lugawe.grm.core.domain.Release;
import de.lugawe.grm.core.exception.GRMException;
import de.lugawe.grm.core.service.GitHubService;

@ApplicationScoped
public class ManagementRestService {

    @Inject
    private JsonConverter jsonConverter;

    @Inject
    private GitHubService gitHubService;

    public ManagementRestService() {}

    public List<JsonRelease> getReleases(String repository) {

        List<Release> releases;
        try {
            releases = gitHubService.getReleases(repository);
        } catch (Exception e) {
            throw new GRMException("Could not get releases from '" + repository + "'", e);
        }

        return releases.stream().map(jsonConverter::toJsonRelease).toList();
    }

    public JsonRelease getRelease(String repository, String releaseName) {

        if (releaseName == null || releaseName.isEmpty()) {
            releaseName = Release.LATEST;
        }

        Release release;
        try {
            release = gitHubService.getRelease(repository, releaseName);
        } catch (Exception e) {
            throw new GRMException("Could not get release '" + releaseName + "' from '" + repository + "'", e);
        }

        return jsonConverter.toJsonRelease(release);
    }
}
