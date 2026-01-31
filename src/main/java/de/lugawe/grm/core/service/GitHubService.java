package de.lugawe.grm.core.service;

import java.util.List;

import de.lugawe.grm.core.domain.Release;

public interface GitHubService {

    List<Release> getReleases(String projectName);

    Release getRelease(String projectName, String releaseName);

    Release getLatestRelease(String projectName);
}
