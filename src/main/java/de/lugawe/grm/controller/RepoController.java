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
    @Path("/{repository}/releases")
    public List<JsonRelease> getReleases(
            @PathParam("repository") String repository,
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("10") int size) {

        return repoControllerService.getReleases(repository, page, size);
    }

    @GET
    @Path("/{repository}/releases/{releaseName}")
    public JsonRelease getRelease(
            @PathParam("repository") String repository, @PathParam("releaseName") String releaseName) {

        return repoControllerService.getRelease(repository, releaseName);
    }

    @GET
    @Path("/{repository}/releases/{releaseName}/assets")
    public List<JsonAsset> getAssets(
            @PathParam("repository") String repository, @PathParam("releaseName") String releaseName) {

        return repoControllerService.getAssets(repository, releaseName);
    }

    @GET
    @Path("/{repository}/releases/{releaseName}/assets/{assetName}")
    public JsonAsset getAsset(
            @PathParam("repository") String repository,
            @PathParam("releaseName") String releaseName,
            @PathParam("assetName") String assetName) {

        return repoControllerService.getAsset(repository, releaseName, assetName);
    }

    @GET
    @Path("/{repository}/releases/{releaseName}/assets/{assetName}/archive")
    public List<JsonArchiveAsset> getArchiveAssets(
            @PathParam("repository") String repository,
            @PathParam("releaseName") String releaseName,
            @PathParam("assetName") String assetName) {

        return repoControllerService.getArchiveAssets(repository, releaseName, assetName);
    }

    @GET
    @Path("/{repository}/releases/{releaseName}/assets/{assetName}/archive/{archiveAssetName}")
    public JsonArchiveAsset getArchiveAsset(
            @PathParam("repository") String repository,
            @PathParam("releaseName") String releaseName,
            @PathParam("assetName") String assetName,
            @PathParam("archiveAssetName") String archiveAssetName) {

        return repoControllerService.getArchiveAsset(repository, releaseName, assetName, archiveAssetName);
    }
}
