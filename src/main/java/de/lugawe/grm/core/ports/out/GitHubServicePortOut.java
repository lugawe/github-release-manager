package de.lugawe.grm.core.ports.out;

import java.util.List;

import de.lugawe.grm.core.domain.Release;
import de.lugawe.grm.core.ports.GitHubServicePort;

public interface GitHubServicePortOut extends GitHubServicePort {

    Release getLatestRelease(String project);

    List<Release> getReleases(String project);
}
