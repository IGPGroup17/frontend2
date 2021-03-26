package com.example.personalprofile.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.personalprofile.AppUser;
import com.example.personalprofile.R;
import com.example.personalprofile.activities.meta.ObservingActivity;
import com.example.personalprofile.http.VolleyQueue;
import com.example.personalprofile.models.Event;
import com.example.personalprofile.repositories.EventModificationRepository;
import com.example.personalprofile.repositories.context.EventModificationContext;
import com.example.personalprofile.repositories.meta.RepositoryConstants;
import com.example.personalprofile.repositories.meta.observer.NotificationContext;
import com.example.personalprofile.views.EventRecyclerViewAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class RemoveEventActivity extends ObservingActivity<Event> {

    private final List<Event> currentEvents = new ArrayList<>();

    private RecyclerView recyclerView;

    private EventRecyclerViewAdapter adapter;

    private EventRecyclerViewAdapter.OnClick onClick = (view, event) -> {
        Log.d("event", new GsonBuilder().setPrettyPrinting().create().toJson(event));
        EventModificationRepository.getInstance().sendRequest(this, EventModificationContext.Delete.of(event.getEventID()));
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_event);

        findViewById(R.id.homebutton).setOnClickListener(listener -> onClickHomeButton());
        findViewById(R.id.chatbutton).setOnClickListener(listener -> onClickChatButton());
        findViewById(R.id.personalprofile).setOnClickListener(listener -> onClickPersonalProfileButton());

        initCurrentEvents();
        this.recyclerView = findViewById(R.id.event_recycler_view);

        this.adapter = new EventRecyclerViewAdapter(this, currentEvents, onClick);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    public void initCurrentEvents() {
        VolleyQueue.getInstance(getApplicationContext()).addRequest(new JsonArrayRequest(Request.Method.GET, RepositoryConstants.EVENTS_ENDPOINT + "all/" + AppUser.getInstance().getGoogleId(), null, response -> {
            Type type = new TypeToken<ArrayList<Event>>() {
            }.getType();
            List<Event> events = new Gson().fromJson(response.toString(), type);
            try {
                Log.d("events", response.toString(2));
            } catch (JSONException exception) {
                exception.printStackTrace();
            }
            currentEvents.clear();
            currentEvents.addAll(events);

            adapter.notifyDataSetChanged();
        }, Throwable::printStackTrace));
    }

    public void onClickHomeButton() {
        Intent intent = new Intent(this, HomePageActivity.class);
        startActivity(intent);
    }

    public void onClickChatButton() {
        Intent intent = new Intent(this, EventChatActivity.class);
        startActivity(intent);
    }

    public void onClickPersonalProfileButton() {
        Intent intent = new Intent(this, OrganiserProfileActivity.class);
        startActivity(intent);
    }

    @Override
    public void onNotification(NotificationContext<Event> notificationContext) {
        Toast.makeText(this, "Removed Event!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, OrganiserProfileActivity.class);
        startActivity(intent);
    }
}
