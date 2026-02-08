package de.lugawe.grm.controller;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

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
    public Response getReleases(
            @PathParam("repository") String repository,
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("10") int size) {

        List<JsonRelease> releases = repoControllerService.getReleases(repository, page, size);

        return Response.ok(releases).build();
    }

    @GET
    @Path("/{repository}/releases/{releaseName}")
    public Response getRelease(
            @PathParam("repository") String repository, @PathParam("releaseName") String releaseName) {

        JsonRelease release = repoControllerService.getRelease(repository, releaseName);

        return Response.ok(release).build();
    }

    @GET
    @Path("/{repository}/releases/{releaseName}/assets")
    public Response getAssets(
            @PathParam("repository") String repository, @PathParam("releaseName") String releaseName) {

        return Response.ok().build();
    }

    @GET
    @Path("/{repository}/releases/{releaseName}/assets/{assetName}")
    public Response getAsset(
            @PathParam("repository") String repository,
            @PathParam("releaseName") String releaseName,
            @PathParam("assetName") String assetName) {

        return Response.ok().build();
    }
}
