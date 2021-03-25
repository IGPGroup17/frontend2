package com.example.personalprofile.repositories.eventsearch;

import com.example.personalprofile.repositories.context.EventSearchContext;
import com.example.personalprofile.util.IAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ElasticSearchQueryAdapter implements IAdapter<EventSearchContext, JSONObject> {

    @Override
    public JSONObject adapt(EventSearchContext options) {
        JSONObject matcher = asObject(EventSearchContext.DESCRIPTOR.getQueryName(), adaptMustOptions(options));
        JSONObject bool = asObject("bool", matcher);
        return asObject("query", bool);
    }

    private JSONArray adaptMustOptions(EventSearchContext options) {
        JSONArray arr = new JSONArray();
        arr.put(matchQuery("message.name", options.getSearchQuery()));

        if (options.isAlcoholFreeEnabled())
            arr.put(matchQuery("message.isAlcoholFree", options.isAlcoholFreeEnabled()));

        if (options.isVirtualEnabled())
            arr.put(matchQuery("message.isVirtual", options.isVirtualEnabled()));

        if (options.isInPersonEnabled())
            arr.put(matchQuery("message.isInPerson", options.isInPersonEnabled()));
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
