package de.lugawe.grm.core.domain;

import java.util.List;

public final class Project {

    private String name;
    private List<Release> releases;

    public Project() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Release> getReleases() {
        return releases;
    }

    public void setReleases(List<Release> releases) {
        this.releases = releases;
    }
}
