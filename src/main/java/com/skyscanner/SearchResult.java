package com.skyscanner;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties({"error"})
public class SearchResult {
    @JsonProperty private String city;

    @JsonProperty private String kind;

    @JsonProperty private String title;

    private String errorMsg;

    public SearchResult() {}

    public SearchResult(String kind) {
        this.kind = kind;
    }

    public String getCity() {
        return city;
    }

    public String getKind() {
        return kind;
    }

    public String getTitle() {
        return title;
    }

    public void setCity(String city) {
        if (city != null && !city.isEmpty()) {
            this.city = city;
        }
        else {
            this.city = "Unknown City";
        }
    }

    public void setTitle(String title) {
        if (title != null && !title.isEmpty()) {
            this.title = title;
        }
        else {
            this.title = "Unknown";
        }
    }

    public void setKind(String kind) {
        if (kind != null && !kind.isEmpty()) {
            this.kind = kind;
        }
        else {
            this.kind = "Unknown";
        }
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
        this.city = "Unknown";
        this.title = errorMsg;
    }

    public boolean isError() {
        return errorMsg != null && !errorMsg.isEmpty();
    }
}
