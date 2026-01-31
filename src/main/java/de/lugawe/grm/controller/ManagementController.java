package de.lugawe.grm.controller;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import de.lugawe.grm.controller.json.JsonRelease;

@Path("/projects")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ManagementController {

    @Inject
    private ManagementRestService managementRestService;

    public ManagementController() {}

    @GET
    @Path("/{projectName}/releases")
    public Response getReleases(@PathParam("projectName") String projectName) {

        List<JsonRelease> releases = managementRestService.getReleases(projectName);

        return Response.ok(releases).build();
    }

    @GET
    @Path("/{projectName}/releases/{versionName}")
    public Response getRelease(
            @PathParam("projectName") String projectName, @PathParam("releaseName") String releaseName) {

        JsonRelease release = managementRestService.getRelease(projectName, releaseName);

        return Response.ok(release).build();
    }
}
