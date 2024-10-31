package com.skyscanner;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Search {
    @JsonProperty private String city;

    @JsonProperty private boolean includeErrors;

    public Search() {
    }

    public Search(String city, boolean includeErrors) {
        this.city = city;
        this.includeErrors = includeErrors;
    }

    public String getCity() {
        return city;
    }

    public boolean getIncludeErrors() {
        return includeErrors;
    }
}
