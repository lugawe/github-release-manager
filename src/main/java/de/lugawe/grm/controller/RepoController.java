package de.lugawe.grm.controller;

import java.io.InputStream;
import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import de.lugawe.grm.controller.json.JsonArchiveAsset;
import de.lugawe.grm.controller.json.JsonAsset;
import de.lugawe.grm.controller.json.JsonRelease;
import de.lugawe.grm.controller.util.ContentTypeUtils;

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
    @Path("/{repository}/releases/{tagName}/assets/{assetName}/content")
    public Response getAssetContent(
            @PathParam("repository") String repository,
            @PathParam("tagName") String tagName,
            @PathParam("assetName") String assetName) {

        InputStream inputStream = repoControllerService.getAssetContent(repository, tagName, assetName);
        String contentType = ContentTypeUtils.getContentType(assetName);

        return Response.ok(inputStream).type(contentType).build();
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
    @Path("/{repository}/releases/{tagName}/assets/{assetName}/archive/{path}")
    public JsonArchiveAsset getArchiveAsset(
            @PathParam("repository") String repository,
            @PathParam("tagName") String tagName,
            @PathParam("assetName") String assetName,
            @PathParam("path") String path) {

        return repoControllerService.getArchiveAsset(repository, tagName, assetName, path);
    }

    @GET
    @Path("/{repository}/releases/{tagName}/assets/{assetName}/archive/{path}/content")
    public Response getArchiveAssetContent(
            @PathParam("repository") String repository,
            @PathParam("tagName") String tagName,
            @PathParam("assetName") String assetName,
            @PathParam("path") String path) {

        InputStream inputStream = repoControllerService.getArchiveAssetContent(repository, tagName, assetName, path);
        String contentType = ContentTypeUtils.getContentType(path);

        return Response.ok(inputStream).type(contentType).build();
    }
}
