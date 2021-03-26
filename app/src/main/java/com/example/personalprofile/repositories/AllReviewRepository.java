package com.example.personalprofile.repositories;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.personalprofile.activities.meta.ObservingActivity;
import com.example.personalprofile.http.VolleyQueue;
import com.example.personalprofile.models.Review;
import com.example.personalprofile.repositories.context.ReviewModificationContext;
import com.example.personalprofile.repositories.meta.AbstractRepository;
import com.example.personalprofile.repositories.meta.RepositoryConstants;
import com.example.personalprofile.repositories.meta.observer.NotificationContext;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class AllReviewRepository extends AbstractRepository<ReviewModificationContext, List<Review>> {

    private static AllReviewRepository instance;

    private AllReviewRepository() {
    }

    public static AllReviewRepository getInstance() {
        if (instance == null) {
            instance = new AllReviewRepository();
        }
        return instance;
    }

    @Override
    public void sendRequest(ObservingActivity<List<Review>> activity, ReviewModificationContext context) {
        attachObserver(activity);

        VolleyQueue queue = VolleyQueue.getInstance(activity.getApplicationContext());
        try {
            if (context instanceof ReviewModificationContext.ReadAll) {
                queue.addRequest(buildAllRequest((ReviewModificationContext.ReadAll) context));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private Request<?> buildAllRequest(ReviewModificationContext.ReadAll context) throws JSONException {
        String url = RepositoryConstants.REVIEW_ENDPOINT + "all/" + context.getOrganiserId();
        return new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {
                    Type type = new TypeToken<ArrayList<Review>>() {
                    }.getType();
                    List<Review> reviews = GSON.fromJson(response.toString(), type);
                    Log.d("reviews", GSON.toJson(reviews));
                    notifyObservers(NotificationContext.of(reviews));
                },
                Throwable::printStackTrace);
    }
}
