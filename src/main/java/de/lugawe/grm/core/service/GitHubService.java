package de.lugawe.grm.core.service;

import java.util.List;

import de.lugawe.grm.core.domain.Release;

public interface GitHubService {

    Release getLatestRelease(String project);

    List<Release> getReleases(String project);
}
