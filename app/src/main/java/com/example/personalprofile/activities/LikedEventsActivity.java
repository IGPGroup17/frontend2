package com.example.personalprofile.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.personalprofile.AppUser;
import com.example.personalprofile.R;
import com.example.personalprofile.http.VolleyQueue;
import com.example.personalprofile.models.Event;
import com.example.personalprofile.models.Review;
import com.example.personalprofile.repositories.meta.RepositoryConstants;
import com.example.personalprofile.views.EventRecyclerViewAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class LikedEventsActivity extends AppCompatActivity {

    private final List<Event> currentEvents = new ArrayList<>();

    private RecyclerView recyclerView;

    private EventRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liked_events);

        findViewById(R.id.homebutton).setOnClickListener(listener -> onClickHomeButton());
        findViewById(R.id.chatbutton).setOnClickListener(listener -> onClickChatButton());
        findViewById(R.id.personalprofile).setOnClickListener(listener -> onClickPersonalProfileButton());

        initCurrentEvents();
        this.recyclerView = findViewById(R.id.event_recycler_view);

        this.adapter = new EventRecyclerViewAdapter(this, currentEvents);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    public void initCurrentEvents() {
        VolleyQueue.getInstance(getApplicationContext()).addRequest(new JsonArrayRequest(Request.Method.GET, RepositoryConstants.STUDENT_ENDPOINT + "liked/" + AppUser.getInstance().getGoogleId(), null, response -> {
            Type type = new TypeToken<ArrayList<Event>>() {
            }.getType();
            List<Event> events = new Gson().fromJson(response.toString(), type);
            Log.d("events", new Gson().toJson(events));
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




}