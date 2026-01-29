package de.lugawe.grm.core.ports.in;

import java.util.List;

import de.lugawe.grm.core.domain.Release;
import de.lugawe.grm.core.ports.out.GitHubServicePortOut;

public class DefaultGitHubServicePortIn implements GitHubServicePortIn {

    private final GitHubServicePortOut gitHubServicePortOut;

    public DefaultGitHubServicePortIn(GitHubServicePortOut gitHubServicePortOut) {
        this.gitHubServicePortOut = gitHubServicePortOut;
    }

    @Override
    public Release getLatestRelease(String project) {
        return gitHubServicePortOut.getLatestRelease(project);
    }

    @Override
    public List<Release> getReleases(String project) {
        return gitHubServicePortOut.getReleases(project);
    }
}
