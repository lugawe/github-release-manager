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
public class ManagementController {

    @Inject
    private ManagementRestService managementRestService;

    public ManagementController() {}

    @GET
    @Path("/{repository}/releases")
    public Response getReleases(@PathParam("repository") String repository) {

        List<JsonRelease> releases = managementRestService.getReleases(repository);

        return Response.ok(releases).build();
    }

    @GET
    @Path("/{repository}/releases/{versionName}")
    public Response getRelease(
            @PathParam("repository") String repository, @PathParam("releaseName") String releaseName) {

        JsonRelease release = managementRestService.getRelease(repository, releaseName);

        return Response.ok(release).build();
    }
}
