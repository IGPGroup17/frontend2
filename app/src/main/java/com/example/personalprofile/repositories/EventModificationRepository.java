package com.example.personalprofile.repositories;

import com.android.volley.Request;
import com.example.personalprofile.activities.CreateEventActivity;
import com.example.personalprofile.http.VolleyQueue;
import com.example.personalprofile.models.Event;
import com.example.personalprofile.repositories.context.EventModificationContext;
import com.example.personalprofile.repositories.meta.AbstractRepository;

public class EventModificationRepository extends AbstractRepository<CreateEventActivity, EventModificationContext, Event> {

    @Override
    public void sendRequest(CreateEventActivity activity, EventModificationContext context) {
        VolleyQueue queue = VolleyQueue.getInstance(activity.getApplicationContext());
        switch (context.getType()) {
            case CREATE:
                queue.addRequest(buildCreateRequest());
                break;
            case DELETE:
                queue.addRequest(buildDeleteRequest());
                break;
            case UPDATE:
                queue.addRequest(buildUpdateRequest());
                break;
            default:
                throw new IllegalStateException("invalid type!");
        }
    }

    private Request<?> buildCreateRequest() {
        return null;
    }


    private Request<?> buildDeleteRequest() {
        return null;
    }


    private Request<?> buildUpdateRequest() {
        return null;
    }
}
