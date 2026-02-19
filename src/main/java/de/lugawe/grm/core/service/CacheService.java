package de.lugawe.grm.core.service;

import java.util.List;
import java.util.Optional;

import de.lugawe.grm.core.domain.Release;
import de.lugawe.grm.core.domain.Repository;

public interface CacheService {

    List<Repository> getRepositories();

    Optional<Repository> getRepositoryByName(String repository);

    List<Release> getReleases(String repository);

    Optional<Release> getReleaseByTagName(String repository, String tagName);
}
