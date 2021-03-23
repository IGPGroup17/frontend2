package com.example.personalprofile.repositories;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.personalprofile.activities.HomePageActivity;
import com.example.personalprofile.http.VolleyQueue;
import com.example.personalprofile.models.Event;
import com.example.personalprofile.repositories.event.ElasticSearchQueryAdapter;
import com.example.personalprofile.repositories.event.ElasticSearchResponseAdapter;
import com.example.personalprofile.repositories.event.EventRequestContext;
import com.example.personalprofile.repositories.meta.AbstractRepository;
import com.example.personalprofile.repositories.meta.observer.IRepositoryObserver;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class EventSearchRepository extends AbstractRepository<HomePageActivity, EventRequestContext, List<Event>> {

    public void sendRequest(HomePageActivity activity, EventRequestContext options) {
        attachObserver(activity);
        Request<?> request = buildRequest(options);
        VolleyQueue.getInstance(activity.getApplicationContext()).addRequest(request);
    }

    private Request<?> buildRequest(EventRequestContext options) {
        JSONObject data = new ElasticSearchQueryAdapter().adapt(options);
        try {
            Log.d("data", data.toString(4));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new JsonObjectRequest(Request.Method.POST, buildUrl(options.getSearchQuery()), data, response -> {
            Log.d("Event Repository", response.toString());
            notifyObservers(IRepositoryObserver.NotificationContext.of(new ElasticSearchResponseAdapter().adapt(response)));
            Log.d("Event Repository", "Notified " + observers.size() + " observers");
        }, Throwable::printStackTrace);
    }

    private String buildUrl(String searchQuery) {
        return RepositoryConstants.ELASITCSEARCH_URL + "?q=" + (searchQuery.equals("") ? "0" : searchQuery.replace(' ', '_'));
    }
}
