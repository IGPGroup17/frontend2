package com.example.personalprofile.menu;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.personalprofile.AppUser;
import com.example.personalprofile.CachedReview;
import com.example.personalprofile.R;
import com.example.personalprofile.activities.CreateReviewActivity;
import com.example.personalprofile.activities.ReadReviewsActivity;
import com.example.personalprofile.http.VolleyQueue;
import com.example.personalprofile.models.Event;
import com.example.personalprofile.repositories.meta.RepositoryConstants;

public class EventPopupMenu implements PopupMenu.OnMenuItemClickListener {

    private final Activity activity;

    private final Event event;

    private final PopupMenu popupMenu;

    public EventPopupMenu(Activity activity, View view, Event event) {
        this.activity = activity;
        this.event = event;
        this.popupMenu = initPopupMenu(activity, view);
    }

    private PopupMenu initPopupMenu(Activity activity, View view) {
        PopupMenu menu = new PopupMenu(activity, view);
        menu.setOnMenuItemClickListener(this);
        menu.inflate(R.menu.event_popup_menu);
        return menu;
    }


    public void show() {
        popupMenu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        int itemId = item.getItemId();
        VolleyQueue queue = VolleyQueue.getInstance(activity.getApplicationContext());
        if (itemId == R.id.popup_like_event) {
            Log.d("popup", "liking event");
            String url = buildUrl("like");
            queue.addRequest(new JsonObjectRequest(Request.Method.PUT, url, null,
                    response -> Toast.makeText(activity.getApplicationContext(), "Liked event!", Toast.LENGTH_SHORT).show(),
                    Throwable::printStackTrace));

        } else if (itemId == R.id.popup_going_event) {
            String url = buildUrl("going");
            queue.addRequest(new JsonObjectRequest(Request.Method.PUT, url, null,
                    response -> Toast.makeText(activity.getApplicationContext(), "Going to event!", Toast.LENGTH_SHORT).show(),
                    Throwable::printStackTrace));
        } else if (itemId == R.id.popup_leave_review) {
            CachedReview.getInstance().assignInitialContext(event.getName(), event.getOrganiserID());
            Intent intent = new Intent(activity, CreateReviewActivity.class);
            activity.startActivity(intent);
        } else if (itemId == R.id.popup_read_reviews) {
            AppUser.getInstance().setCurrentReviewOrganiserId(event.getOrganiserID());
            Intent intent = new Intent(activity, ReadReviewsActivity.class);
            activity.startActivity(intent);
        } else {
            return false;
        }

        return true;
    }

    private String buildUrl(String endpoint) {
        return RepositoryConstants.STUDENT_ENDPOINT + endpoint + "/" + AppUser.getInstance().getGoogleId() + "/" + event.getEventID();
    }
}
