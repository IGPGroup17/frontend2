package com.example.personalprofile.repositories;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.personalprofile.activities.meta.ObservingActivity;
import com.example.personalprofile.http.VolleyQueue;
import com.example.personalprofile.models.Event;
import com.example.personalprofile.repositories.eventsearch.ElasticSearchQueryAdapter;
import com.example.personalprofile.repositories.eventsearch.ElasticSearchResponseAdapter;
import com.example.personalprofile.repositories.context.EventSearchContext;
import com.example.personalprofile.repositories.meta.AbstractRepository;
import com.example.personalprofile.repositories.meta.RepositoryConstants;
import com.example.personalprofile.repositories.meta.observer.NotificationContext;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class EventSearchRepository extends AbstractRepository<EventSearchContext, List<Event>> {



    public void sendRequest(ObservingActivity<List<Event>> activity, EventSearchContext options) {
        attachObserver(activity);
        Request<?> request = buildRequest(options);
        VolleyQueue.getInstance(activity.getApplicationContext()).addRequest(request);
    }

    private Request<?> buildRequest(EventSearchContext options) {
        JSONObject data = new ElasticSearchQueryAdapter().adapt(options);
        try {
            Log.d("data", data.toString(4));
        } catch (JSONException e) {
            e.printStackTrace();
        }

//        return buildSimpleSearch(options.getSearchQuery());
        return new JsonObjectRequest(Request.Method.POST, buildUrl(options.getSearchQuery()), data, response -> {
            Log.d("Event Repository", response.toString());
            notifyObservers(NotificationContext.of(new ElasticSearchResponseAdapter().adapt(response)));
            Log.d("Event Repository", "Notified " + observers.size() + " observers");
        }, Throwable::printStackTrace);
    }

    private String buildUrl(String searchQuery) {
        return RepositoryConstants.ELASITCSEARCH_URL + "?q=" + (searchQuery.equals("") ? "0" : searchQuery.replace(" ", "%20"));
    }

    private Request<?> buildSimpleSearch(String name) {
        return new JsonObjectRequest(Request.Method.GET, buildUrl(name), null, response -> notifyObservers(NotificationContext.of(new ElasticSearchResponseAdapter().adapt(response))), Throwable::printStackTrace);
    }
}
