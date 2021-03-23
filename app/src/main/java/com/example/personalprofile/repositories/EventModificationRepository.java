package com.example.personalprofile.repositories;

import android.app.Activity;

import com.android.volley.Request;
import com.example.personalprofile.activities.meta.ObserverActivity;
import com.example.personalprofile.http.VolleyQueue;
import com.example.personalprofile.models.Event;
import com.example.personalprofile.repositories.context.EventModificationContext;
import com.example.personalprofile.repositories.meta.AbstractRepository;

public class EventModificationRepository extends AbstractRepository<EventModificationContext, Event> {

    @Override
    public void sendRequest(ObserverActivity<Event> activity, EventModificationContext context) {
        attachObserver(activity);

        VolleyQueue queue = VolleyQueue.getInstance(activity.getApplicationContext());
        if (context instanceof EventModificationContext.Create) {
            queue.addRequest(buildCreateRequest((EventModificationContext.Create) context));

        } else if (context instanceof EventModificationContext.Update) {
            queue.addRequest(buildUpdateRequest((EventModificationContext.Update) context));

        } else if (context instanceof EventModificationContext.Delete) {
            queue.addRequest(buildDeleteRequest((EventModificationContext.Delete) context));
        }
    }

    private Request<?> buildCreateRequest(EventModificationContext.Create context) {
        return null;
    }


    private Request<?> buildDeleteRequest(EventModificationContext.Delete context) {
        return null;
    }


    private Request<?> buildUpdateRequest(EventModificationContext.Update context) {
        return null;
    }
}
