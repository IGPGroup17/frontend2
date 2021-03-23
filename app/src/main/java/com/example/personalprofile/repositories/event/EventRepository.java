package com.example.personalprofile.repositories.event;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.personalprofile.http.VolleyQueue;
import com.example.personalprofile.repositories.RepositoryConstants;

import org.json.JSONObject;

public class EventRepository {

    public void sendRequest(EventSearchOptions options) {
        Request<?> request = buildRequest(options);
        VolleyQueue.getInstance().addRequest(request);
    }

    private Request<?> buildRequest(EventSearchOptions options) {
        JSONObject data = new ElasticSearchQueryAdapter().adapt(options);
        return new JsonObjectRequest(Request.Method.POST, RepositoryConstants.ELASITCSEARCH_URL, data, response -> Log.d("response", response.toString()), Throwable::printStackTrace);
    }
}
