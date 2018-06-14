package com.microvish.dropwizard.api.resource;

import com.microvish.dropwizard.api.function.CheckedSupplier;
import com.microvish.dropwizard.api.model.exception.InvalidRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public abstract class Resource {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    protected Response apiCall(final String operationName, final CheckedSupplier<Response, Exception> supplier) {
        log.info("[ENTRY] - apiOperation={}", operationName);

        try {
            final Response response = supplier.get();

            log.debug("[ENTRY] - apiOperation={}, response={}", operationName, response);

            return response;
        } catch (final Throwable throwable) {
            log.error("API encountered an exception", throwable);

            final WebApplicationException webApplicationException;

            if (throwable instanceof WebApplicationException) {
                webApplicationException = WebApplicationException.class.cast(throwable);
            } else if (throwable instanceof InvalidRequestException) {
                webApplicationException = new WebApplicationException(throwable.getMessage(), Response.Status.NOT_ACCEPTABLE);
            } else {
                webApplicationException = new WebApplicationException("Oops something went wrong!");
            }

            throw webApplicationException;
        } finally {
            log.info("[EXIT] - apiOperation={}", operationName);
        }
    }

    protected Response okResponse(final Object entity) {
        return Response.status(Response.Status.OK).entity(entity).build();
    }
}
