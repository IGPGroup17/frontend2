package com.example.personalprofile.menu;

import android.app.Activity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.personalprofile.AppUser;
import com.example.personalprofile.R;
import com.example.personalprofile.http.VolleyQueue;
import com.example.personalprofile.repositories.meta.RepositoryConstants;

public class EventPopupMenu implements PopupMenu.OnMenuItemClickListener {

    private final Activity activity;

    private final String eventId;

    private final PopupMenu popupMenu;

    public EventPopupMenu(Activity activity, View view, String eventId) {
        this.activity = activity;
        this.eventId = eventId;
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

            String url = buildUrl("like");
            queue.addRequest(new JsonObjectRequest(Request.Method.PUT, url, null,
                    response -> Toast.makeText(activity.getApplicationContext(), "Liked event!", Toast.LENGTH_SHORT).show(),
                    Throwable::printStackTrace));

        } else if (itemId == R.id.popup_interested_event) {
            String url = buildUrl("interested");
            queue.addRequest(new JsonObjectRequest(Request.Method.PUT, url, null,
                    response -> Toast.makeText(activity.getApplicationContext(), "Interested in event!", Toast.LENGTH_SHORT).show(),
                    Throwable::printStackTrace));
        } else if (itemId == R.id.popup_going_event) {
            String url = buildUrl("going");
            queue.addRequest(new JsonObjectRequest(Request.Method.PUT, url, null,
                    response -> Toast.makeText(activity.getApplicationContext(), "Going to event!", Toast.LENGTH_SHORT).show(),
                    Throwable::printStackTrace));
        } else {
            return false;
        }

        return true;
    }

    private String buildUrl(String endpoint) {
        return RepositoryConstants.STUDENT_ENDPOINT + endpoint + "/" + AppUser.getInstance().getGoogleId() + "/" + eventId;
    }
}
