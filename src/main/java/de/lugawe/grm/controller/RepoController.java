package de.lugawe.grm.controller;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import de.lugawe.grm.controller.json.JsonArchiveAsset;
import de.lugawe.grm.controller.json.JsonAsset;
import de.lugawe.grm.controller.json.JsonRelease;

@Path("/repos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RepoController {

    @Inject
    private RepoControllerService repoControllerService;

    public RepoController() {}

    @GET
    @Path("/{repository}/releases/{tagName}")
    public JsonRelease getRelease(@PathParam("repository") String repository, @PathParam("tagName") String tagName) {

        return repoControllerService.getRelease(repository, tagName);
    }

    @GET
    @Path("/{repository}/releases/{tagName}/assets")
    public List<JsonAsset> getAssets(@PathParam("repository") String repository, @PathParam("tagName") String tagName) {

        return repoControllerService.getAssets(repository, tagName);
    }

    @GET
    @Path("/{repository}/releases/{tagName}/assets/{assetName}")
    public JsonAsset getAsset(
            @PathParam("repository") String repository,
            @PathParam("tagName") String tagName,
            @PathParam("assetName") String assetName) {

        return repoControllerService.getAsset(repository, tagName, assetName);
    }

    @GET
    @Path("/{repository}/releases/{tagName}/assets/{assetName}/archive")
    public List<JsonArchiveAsset> getArchiveAssets(
            @PathParam("repository") String repository,
            @PathParam("tagName") String tagName,
            @PathParam("assetName") String assetName) {

        return repoControllerService.getArchiveAssets(repository, tagName, assetName);
    }

    @GET
    @Path("/{repository}/releases/{tagName}/assets/{assetName}/archive/{archiveAssetName}")
    public JsonArchiveAsset getArchiveAsset(
            @PathParam("repository") String repository,
            @PathParam("tagName") String tagName,
            @PathParam("assetName") String assetName,
            @PathParam("archiveAssetName") String archiveAssetName) {

        return repoControllerService.getArchiveAsset(repository, tagName, assetName, archiveAssetName);
    }
}
