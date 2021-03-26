package com.example.personalprofile.repositories;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.personalprofile.Review;
import com.example.personalprofile.activities.meta.ObservingActivity;
import com.example.personalprofile.http.VolleyQueue;
import com.example.personalprofile.repositories.context.ReviewModificationContext;
import com.example.personalprofile.repositories.meta.AbstractRepository;
import com.example.personalprofile.repositories.meta.RepositoryConstants;
import com.example.personalprofile.repositories.meta.observer.NotificationContext;

import org.json.JSONException;
import org.json.JSONObject;

public class ReviewModificationRepository extends AbstractRepository<ReviewModificationContext, Review> {

    private static ReviewModificationRepository instance;

    private ReviewModificationRepository() {}

    public static ReviewModificationRepository getInstance() {
        if (instance == null) {
            instance = new ReviewModificationRepository();
        }
        return instance;
    }

    @Override
    public void sendRequest(ObservingActivity<Review> activity, ReviewModificationContext context) {
        attachObserver(activity);

        VolleyQueue queue = VolleyQueue.getInstance(activity.getApplicationContext());
        try {
            if (context instanceof ReviewModificationContext.Create) {
                queue.addRequest(buildCreateRequest((ReviewModificationContext.Create) context));

            } else if (context instanceof ReviewModificationContext.Read) {
                queue.addRequest(buildGetRequest((ReviewModificationContext.Read) context));

            } else if (context instanceof ReviewModificationContext.Update) {
                queue.addRequest(buildUpdateRequest((ReviewModificationContext.Update) context));

            } else if (context instanceof ReviewModificationContext.Delete) {
                queue.addRequest(buildDeleteRequest((ReviewModificationContext.Delete) context));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private Request<?> buildGetRequest(ReviewModificationContext.Read context) {
        String url = RepositoryConstants.EVENTS_ENDPOINT + context.getReviewId();
        return new JsonObjectRequest(Request.Method.GET, url, null,
                response -> notifyObservers(NotificationContext.of(GSON.fromJson(response.toString(), Review.class))),
                Throwable::printStackTrace);
    }

    private Request<?> buildCreateRequest(ReviewModificationContext.Create context) throws JSONException {
        Log.d("content", GSON.toJson(context.getReview()));
        return new JsonObjectRequest(Request.Method.POST, RepositoryConstants.EVENTS_ENDPOINT, new JSONObject(GSON.toJson(context.getReview())),
                response -> notifyObservers(NotificationContext.of(GSON.fromJson(response.toString(), Review.class))),
                Throwable::printStackTrace);
    }


    private Request<?> buildDeleteRequest(ReviewModificationContext.Delete context) {
        String url = RepositoryConstants.EVENTS_ENDPOINT + context.getReviewId();
        return new JsonObjectRequest(Request.Method.DELETE, url, null,
                response -> notifyObservers(NotificationContext.of(GSON.fromJson(response.toString(), Review.class))),
                Throwable::printStackTrace);
    }


    private Request<?> buildUpdateRequest(ReviewModificationContext.Update context) throws JSONException {
        return new JsonObjectRequest(Request.Method.PUT, RepositoryConstants.EVENTS_ENDPOINT, new JSONObject(GSON.toJson(context.getReview())),
                response -> notifyObservers(NotificationContext.of(GSON.fromJson(response.toString(), Review.class))),
                Throwable::printStackTrace);
    }
}
