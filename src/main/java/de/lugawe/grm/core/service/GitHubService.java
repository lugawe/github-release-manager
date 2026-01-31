package de.lugawe.grm.core.service;

import java.util.List;

import de.lugawe.grm.core.domain.Release;

public interface GitHubService {

    List<Release> getReleases(String repository);

    Release getRelease(String repository, String releaseName);

    default Release getLatestRelease(String repository) {
        return getRelease(repository, Release.LATEST);
    }
}
