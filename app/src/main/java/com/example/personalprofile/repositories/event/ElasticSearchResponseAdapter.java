package com.example.personalprofile.repositories.event;

import com.example.personalprofile.models.Event;
import com.example.personalprofile.util.IAdapter;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.List;

public class ElasticSearchResponseAdapter implements IAdapter<JSONObject, List<Event>> {

    @Override
    public List<Event> adapt(JSONObject json) {
        return null;
    }
}
