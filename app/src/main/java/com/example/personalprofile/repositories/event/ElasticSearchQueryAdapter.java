package com.example.personalprofile.repositories.event;

import com.example.personalprofile.util.IAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ElasticSearchQueryAdapter implements IAdapter<EventSearchOptions, JSONObject> {

    @Override
    public JSONObject adapt(EventSearchOptions options) {
        JSONObject matcher = asObject(EventSearchOptions.DESCRIPTOR.getQueryName(), adaptOptions(options));
        JSONObject bool = asObject("bool", matcher);
        return asObject("query", bool);
    }

    private List<JSONObject> adaptOptions(EventSearchOptions options) {
        List<JSONObject> list = new ArrayList<>();
        list.add(matchQuery("name", options.getSearchQuery()));
        list.add(matchQuery("isAlcoholFree", options.isAlcoholFreeEnabled()));
        list.add(matchQuery("isVirtual", options.isVirtualEnabled()));
        list.add(matchQuery("isInPerson", options.isInPersonEnabled()));
        return list;
    }

    private JSONObject matchQuery(String field, Object value) {
        JSONObject query = asObject(field, value);
        return asObject("match", query);
    }

    private JSONObject asObject(String key, Object value) {
        JSONObject object = new JSONObject();
        try {
            object.put(key, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }
}
