package com.example.personalprofile.repositories.event;

import com.example.personalprofile.models.Event;
import com.example.personalprofile.repositories.IAdapter;

import java.util.List;

public class ElasticSearchResponseAdapter implements IAdapter<List<Event>> {
    @Override
    public List<Event> adapt(String json) {
        return null;
    }
}
