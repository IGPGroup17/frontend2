package com.example.personalprofile.repositories.eventsearch;

import lombok.Getter;

public enum ElasticSearchFieldsDescriptor {
    SHOULD("should"),
    MUST("must");

    @Getter
    private final String queryName;

    ElasticSearchFieldsDescriptor(String queryName) {
        this.queryName = queryName;
    }
}
