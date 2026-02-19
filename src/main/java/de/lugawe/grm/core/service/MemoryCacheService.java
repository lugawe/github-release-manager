package de.lugawe.grm.core.service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import de.lugawe.grm.core.domain.Release;
import de.lugawe.grm.core.domain.Repository;

public class MemoryCacheService implements CacheService {

    private final Map<String, Repository> cache = new ConcurrentHashMap<>();

    public MemoryCacheService() {}

    @Override
    public void put(String repositoryName, Release release) {
        cache.compute(repositoryName, (_, repository) -> {
            if (repository == null) {
                repository = new Repository();
                repository.setName(repositoryName);
                repository.setReleases(new ArrayList<>());
            }
            List<Release> releases = repository.getReleases();
            if (!releases.contains(release)) {
                releases.add(release);
            }
            return repository;
        });
    }

    @Override
    public Collection<Repository> getRepositories() {
        return Collections.unmodifiableCollection(cache.values());
    }

    @Override
    public Optional<Repository> getRepositoryByName(String repositoryName) {
        return Optional.ofNullable(cache.get(repositoryName));
    }

    @Override
    public Collection<Release> getReleases(String repositoryName) {
        return getRepositoryByName(repositoryName)
                .map(repo -> Collections.unmodifiableCollection(repo.getReleases()))
                .orElse(Collections.emptyList());
    }

    @Override
    public Optional<Release> getReleaseByTagName(String repositoryName, String tagName) {
        return getReleases(repositoryName).stream()
                .filter(r -> tagName.equals(r.getName()))
                .findFirst();
    }
}
