package com.microvish.dropwizard.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.microvish.dropwizard.api.resource.ApiResource;
import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

public class ApiApplication extends Application<ApiConfiguration> {
    private static final String APPLICATION_NAME = "dropwizard-api";

    public static void main(String[] args) throws Exception {
        new ApiApplication().run(args);
    }

    @Override
    public String getName() {
        return APPLICATION_NAME;
    }

    @Override
    public void initialize(Bootstrap<ApiConfiguration> bootstrap) {
        // Enable variable substitution with environment variables
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(bootstrap.getConfigurationSourceProvider(), new EnvironmentVariableSubstitutor(false)));

        // Configure ObjectMapper
        bootstrap.getObjectMapper()
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .setSerializationInclusion(JsonInclude.Include.NON_EMPTY)
                .registerModule(new JavaTimeModule());
    }

    @Override
    public void run(final ApiConfiguration apiConfiguration, final Environment environment) throws Exception {
        final ApiModule coreModule = new ApiModule(apiConfiguration, environment);
        final Injector injector = Guice.createInjector(coreModule);

        // Register resources
        environment.jersey().register(injector.getInstance(ApiResource.class));

        // CORS Filter for WebApp testing: http://software.dzhuvinov.com/cors-filter.html
        final FilterRegistration.Dynamic corsFilter = environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        corsFilter.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "*");
        corsFilter.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
        corsFilter.setInitParameter(CrossOriginFilter.ALLOWED_HEADERS_PARAM, "*");
        corsFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "*");

    }
}
