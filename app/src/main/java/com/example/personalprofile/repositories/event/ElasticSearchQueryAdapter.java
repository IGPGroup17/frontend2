package com.example.personalprofile.repositories.event;

import com.example.personalprofile.util.IAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ElasticSearchQueryAdapter implements IAdapter<EventRequestContext, JSONObject> {

    @Override
    public JSONObject adapt(EventRequestContext options) {
        JSONObject matcher = asObject(EventRequestContext.DESCRIPTOR.getQueryName(), adaptMustOptions(options));
        JSONObject bool = asObject("bool", matcher);
        return asObject("query", bool);
    }

    private JSONArray adaptMustOptions(EventRequestContext options) {
        JSONArray arr = new JSONArray();
        arr.put(matchQuery("name", options.getSearchQuery()));

        if (options.isAlcoholFreeEnabled())
            arr.put(matchQuery("isAlcoholFree", options.isAlcoholFreeEnabled()));

        if (options.isVirtualEnabled())
            arr.put(matchQuery("isVirtual", options.isVirtualEnabled()));

        if (options.isInPersonEnabled())
            arr.put(matchQuery("isInPerson", options.isInPersonEnabled()));
        return arr;
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
