package de.lugawe.grm.core.service;

import java.util.List;

import de.lugawe.grm.core.domain.Release;

public interface GitHubService {

    List<Release> getReleases(String repository) throws Exception;

    Release getRelease(String repository, String releaseName) throws Exception;

    default Release getLatestRelease(String repository) throws Exception {
        return getRelease(repository, Release.LATEST);
    }
}
