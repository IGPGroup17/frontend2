package com.example.personalprofile.repositories.event;

import android.app.Activity;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.personalprofile.http.VolleyQueue;
import com.example.personalprofile.repositories.RepositoryConstants;

import org.json.JSONException;
import org.json.JSONObject;

public class EventRepository {

    public void sendRequest(Activity activity, EventSearchOptions options) {
        Request<?> request = buildRequest(options);
        VolleyQueue.getInstance(activity.getApplicationContext()).addRequest(request);
    }

    private Request<?> buildRequest(EventSearchOptions options) {
        JSONObject data = new ElasticSearchQueryAdapter().adapt(options);
        try {
            Log.d("data", data.toString(4));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new JsonObjectRequest(Request.Method.POST, RepositoryConstants.ELASITCSEARCH_URL, data, response -> Log.d("response", response.toString()), Throwable::printStackTrace);
    }
}
