package de.lugawe.grm.core.domain;

import java.util.List;

public final class Repository {

    private String name;
    private List<Release> releases;

    public Repository() {}

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
