package com.microvish.dropwizard.api;

import com.google.inject.AbstractModule;
import com.microvish.dropwizard.api.resource.ApiResource;
import io.dropwizard.setup.Environment;

public class ApiModule extends AbstractModule {
    private final ApiConfiguration apiConfiguration;
    private final Environment environment;

    public ApiModule(final ApiConfiguration apiConfiguration, final Environment environment) {
        this.apiConfiguration = apiConfiguration;
        this.environment = environment;
    }

    @Override
    protected void configure() {
        //Configurations
        bind(ApiConfiguration.class).toInstance(this.apiConfiguration);

        //Resources
        bind(ApiResource.class);
    }
}
