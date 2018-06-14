package com.microvish.dropwizard.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Result {
    @JsonProperty
    private Boolean status;

    public Result() {
    }

    public Result(final boolean status) {
        this.status = Boolean.valueOf(status);
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(final Boolean status) {
        this.status = status;
    }
}
