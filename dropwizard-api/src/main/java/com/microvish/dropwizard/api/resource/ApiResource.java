package com.microvish.dropwizard.api.resource;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.microvish.dropwizard.api.ApiConfiguration;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.InetAddress;

@Path(ApiResource.BASE_PATH)
@Produces(MediaType.APPLICATION_JSON)
@Singleton
public class ApiResource extends Resource {
    public static final String BASE_PATH = "/api";

    @Inject
    private ApiConfiguration apiConfiguration;

    @GET
    @Path("/hello")
    public Response get() {
        return apiCall("hello", () -> okResponse("Hello World! From " + InetAddress.getLocalHost().getHostName()));
    }

    @GET
    @Path("/my-secret")
    public Response mySecret() {
        return apiCall("mySecret", () -> okResponse("My secret is " + this.apiConfiguration.getMySecret()));
    }

    @GET
    @Path("/healthcheck")
    public Response healthCheck() {
        return apiCall("healthCheck", () -> okResponse("API is healthy on  " + InetAddress.getLocalHost().getHostName()));
    }
}
