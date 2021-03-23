package com.example.personalprofile.repositories.context;

import com.example.personalprofile.repositories.eventsearch.ElasticSearchFieldsDescriptor;
import com.example.personalprofile.repositories.meta.RequestContext;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class EventSearchContext implements RequestContext {

    public static final ElasticSearchFieldsDescriptor DESCRIPTOR = ElasticSearchFieldsDescriptor.SHOULD;

    private String searchQuery;

    private boolean isAlcoholFreeEnabled;

    private boolean isVirtualEnabled;

    private boolean isInPersonEnabled;
}
