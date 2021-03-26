package com.example.personalprofile.repositories;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.personalprofile.activities.meta.ObservingActivity;
import com.example.personalprofile.http.VolleyQueue;
import com.example.personalprofile.models.Event;
import com.example.personalprofile.repositories.context.EventModificationContext;
import com.example.personalprofile.repositories.meta.AbstractRepository;
import com.example.personalprofile.repositories.meta.RepositoryConstants;
import com.example.personalprofile.repositories.meta.observer.NotificationContext;

import org.json.JSONException;
import org.json.JSONObject;

public class EventModificationRepository extends AbstractRepository<EventModificationContext, Event> {

    private static EventModificationRepository instance;

    // private to stop init outside of class - use getInstance();
    private EventModificationRepository() {
    }

    public static EventModificationRepository getInstance() {
        if (instance == null) {
            instance = new EventModificationRepository();
        }
        return instance;
    }

    @Override
    public void sendRequest(ObservingActivity<Event> activity, EventModificationContext context) {
        attachObserver(activity);

        VolleyQueue queue = VolleyQueue.getInstance(activity.getApplicationContext());
        try {
            if (context instanceof EventModificationContext.Create) {
                queue.addRequest(buildCreateRequest((EventModificationContext.Create) context));

            } else if (context instanceof EventModificationContext.Read) {
                queue.addRequest(buildGetRequest((EventModificationContext.Read) context));

            } else if (context instanceof EventModificationContext.Update) {
                queue.addRequest(buildUpdateRequest((EventModificationContext.Update) context));

            } else if (context instanceof EventModificationContext.Delete) {
                queue.addRequest(buildDeleteRequest((EventModificationContext.Delete) context));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private Request<?> buildGetRequest(EventModificationContext.Read context) {
        String url = RepositoryConstants.EVENTS_ENDPOINT + context.getEventId();
        return new JsonObjectRequest(Request.Method.GET, url, null,
                response -> notifyObservers(NotificationContext.of(GSON.fromJson(response.toString(), Event.class))),
                Throwable::printStackTrace);
    }

    private Request<?> buildCreateRequest(EventModificationContext.Create context) throws JSONException {
        Log.d("content", GSON.toJson(context.getEvent()));
        return new JsonObjectRequest(Request.Method.POST, RepositoryConstants.EVENTS_ENDPOINT, new JSONObject(GSON.toJson(context.getEvent())),
                response -> notifyObservers(NotificationContext.of(GSON.fromJson(response.toString(), Event.class))),
                Throwable::printStackTrace);
    }


    private Request<?> buildDeleteRequest(EventModificationContext.Delete context) {
        String url = RepositoryConstants.EVENTS_ENDPOINT + context.getEventId();
        return new JsonObjectRequest(Request.Method.DELETE, url, null,
                response -> notifyObservers(NotificationContext.of(GSON.fromJson(response.toString(), Event.class))),
                Throwable::printStackTrace);
    }


    private Request<?> buildUpdateRequest(EventModificationContext.Update context) throws JSONException {
        return new JsonObjectRequest(Request.Method.PUT, RepositoryConstants.EVENTS_ENDPOINT, new JSONObject(GSON.toJson(context.getEvent())),
                response -> notifyObservers(NotificationContext.of(GSON.fromJson(response.toString(), Event.class))),
                Throwable::printStackTrace);
    }
}
