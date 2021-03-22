package com.example.personalprofile.repositories.event;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.example.personalprofile.http.VolleyQueue;

public class EventRepository {

    public static void sendRequest(EventSearchOptions options) {
        Request<?> request = buildRequest(options);
        VolleyQueue.getInstance().addRequest(request);
    }

    private static Request<?> buildRequest(EventSearchOptions options) {
        return null;
    }
}
