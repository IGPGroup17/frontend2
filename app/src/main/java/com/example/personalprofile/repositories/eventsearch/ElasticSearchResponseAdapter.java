package com.example.personalprofile.repositories.eventsearch;

import com.example.personalprofile.models.Event;
import com.example.personalprofile.util.IAdapter;
import com.example.personalprofile.util.JSONArrayUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ElasticSearchResponseAdapter implements IAdapter<JSONObject, List<Event>> {

    @Override
    public List<Event> adapt(JSONObject json) {
        List<Event> events = new ArrayList<>();

        try {
            JSONArray array = json.getJSONObject("hits").getJSONArray("hits");
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                JSONObject message = object.getJSONObject("_source").getJSONObject("message");

                Event event = getEventFrom(message);
                events.add(event);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return events;
    }

    private Event getEventFrom(JSONObject object) throws JSONException {
        return Event.builder()
                .eventId(object.getString("eventId"))
                .scheduledTime(object.getString("scheduledTime"))
                .organiserId(object.getString("organiserId"))
                .goingUsersIDs(JSONArrayUtil.toList(object.getJSONArray("goingUserIds"), String.class))
                .interestedUsersIDs(JSONArrayUtil.toList(object.getJSONArray("interestedUserIds"), String.class))
                .name(object.getString("name"))
                .description(object.getString("description"))
                .state(object.getString("state"))
                .likes(object.getInt("likes"))
                .build();
    }
}
