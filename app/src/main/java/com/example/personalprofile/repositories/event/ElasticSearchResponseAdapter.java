package com.example.personalprofile.repositories.event;

import com.example.personalprofile.models.Event;
import com.example.personalprofile.util.IAdapter;
import com.google.gson.JsonObject;

import java.util.List;

public class ElasticSearchResponseAdapter implements IAdapter<JsonObject, List<Event>> {

    @Override
    public List<Event> adapt(JsonObject json) {
        return null;
    }
}
