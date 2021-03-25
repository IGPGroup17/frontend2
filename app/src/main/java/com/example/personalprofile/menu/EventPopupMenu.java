package com.example.personalprofile.menu;

import android.app.Activity;
import android.view.MenuItem;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.personalprofile.AppUser;
import com.example.personalprofile.R;
import com.example.personalprofile.activities.HomePageActivity;
import com.example.personalprofile.http.VolleyQueue;
import com.example.personalprofile.login.GoogleSignIn;
import com.example.personalprofile.models.Event;
import com.example.personalprofile.repositories.meta.RepositoryConstants;

public class EventPopupMenu implements PopupMenu.OnMenuItemClickListener {

    private final Activity activity;

    private final String eventId;

    public EventPopupMenu(Activity activity, String eventId) {
        this.activity = activity;
        this.eventId = eventId;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        int itemId = item.getItemId();
        VolleyQueue queue = VolleyQueue.getInstance(activity.getApplicationContext());
        if (itemId == R.id.popup_like_event) {
            String url = RepositoryConstants.STUDENT_ENDPOINT + AppUser.getInstance().getGoogleId() + "/" + eventId;
            queue.addRequest(new JsonObjectRequest(Request.Method.PUT, url, null,
                    response -> Toast.makeText(activity.getApplicationContext(), "Liked event!", Toast.LENGTH_SHORT).show(),
                    Throwable::printStackTrace));
        } else if (itemId == R.id.popup_interested_event) {

        } else if (itemId == R.id.popup_going_event) {

        } else {
            return false;
        }

        return true;
    }
}
