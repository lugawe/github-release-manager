package de.lugawe.grm.core.service;

import java.util.List;

import de.lugawe.grm.core.domain.Release;

public interface GitHubService {

    List<Release> getReleases(String projectName);

    Release getRelease(String projectName, String releaseName);

    default Release getLatestRelease(String projectName) {
        return getRelease(projectName, Release.LATEST);
    }
}
