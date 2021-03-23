package com.example.personalprofile.http;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleyQueue {

    private static VolleyQueue instance;

    private RequestQueue queue;

    private VolleyQueue(Context context) {
        this.queue = Volley.newRequestQueue(context.getApplicationContext());
        this.queue.start();

        instance = this;
    }

    public static VolleyQueue init(Context context) {
        return new VolleyQueue(context);
    }

    public static VolleyQueue getInstance() {
        return instance;
    }

    public void addRequest(Request<?> request) {
        queue.add(request);
    }

    public void destroy() {
        this.queue.stop();
        this.queue = null;
        instance = null;
    }
}
