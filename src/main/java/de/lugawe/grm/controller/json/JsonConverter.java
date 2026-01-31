package de.lugawe.grm.controller.json;

import jakarta.enterprise.context.ApplicationScoped;

import de.lugawe.grm.core.domain.Release;

@ApplicationScoped
public class JsonConverter {

    public JsonConverter() {}

    public JsonRelease toJsonRelease(Release release) {

        JsonRelease result = new JsonRelease();
        result.setName(release.getName());

        return result;
    }
}
