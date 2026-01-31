package de.lugawe.grm.controller;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import de.lugawe.grm.controller.json.JsonConverter;
import de.lugawe.grm.controller.json.JsonRelease;
import de.lugawe.grm.core.service.GitHubService;

@ApplicationScoped
public class ManagementRestService {

    @Inject
    private JsonConverter jsonConverter;

    @Inject
    private GitHubService gitHubService;

    public ManagementRestService() {}

    public List<JsonRelease> getReleases(String projectName) {

        return null;
    }

    public JsonRelease getRelease(String projectName, String releaseName) {

        return null;
    }
}
