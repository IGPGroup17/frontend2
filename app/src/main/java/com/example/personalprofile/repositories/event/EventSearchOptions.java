package com.example.personalprofile.repositories.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class EventSearchOptions {

    public static final ElasticSearchFieldsDescriptor DESCRIPTOR = ElasticSearchFieldsDescriptor.MUST;

    private String searchQuery;

    private boolean isAlcoholFreeEnabled;

    private boolean isVirtualEnabled;

    private boolean isInPersonEnabled;
}
