package com.microvish.dropwizard.api;

import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotBlank;

public class ApiConfiguration extends Configuration {
    @NotBlank
    private String mySecret;

    public String getMySecret() {
        return mySecret;
    }

    public void setMySecret(final String mySecret) {
        this.mySecret = mySecret;
    }
}
