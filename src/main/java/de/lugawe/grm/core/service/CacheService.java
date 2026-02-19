package de.lugawe.grm.core.service;

import java.util.Collection;
import java.util.Optional;

import de.lugawe.grm.core.domain.Release;
import de.lugawe.grm.core.domain.Repository;

public interface CacheService {

    void put(String repository, Release release);

    Collection<Repository> getRepositories();

    Optional<Repository> getRepositoryByName(String repository);

    Collection<Release> getReleases(String repository);

    Optional<Release> getReleaseByTagName(String repository, String tagName);
}
