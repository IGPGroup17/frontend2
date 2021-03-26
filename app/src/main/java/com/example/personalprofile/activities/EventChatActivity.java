package com.example.personalprofile.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.personalprofile.AppUser;
import com.example.personalprofile.R;
import com.example.personalprofile.http.VolleyQueue;
import com.example.personalprofile.models.Event;
import com.example.personalprofile.models.Review;
import com.example.personalprofile.repositories.meta.RepositoryConstants;
import com.example.personalprofile.views.ChatWindowRecylerViewAdapter;
import com.example.personalprofile.views.EventRecyclerViewAdapter;
import com.example.personalprofile.views.models.ChatWindow;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EventChatActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private ChatWindowRecylerViewAdapter adapter;

    private final List<ChatWindow> currentWindows = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_chat);

        this.recyclerView = findViewById(R.id.chat_recycler_view);

        this.adapter = new ChatWindowRecylerViewAdapter(currentWindows);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        findViewById(R.id.personalprofile).setOnClickListener(listener -> onClickPersonalProfileButton());
        findViewById(R.id.homebutton).setOnClickListener(listener -> onClickHomeButton());
        findViewById(R.id.likedevents).setOnClickListener(listener -> onClickLikedEventsButton());

        initChatWindows();
    }

    private void initChatWindows() {
        VolleyQueue.getInstance(getApplicationContext()).addRequest(new JsonArrayRequest(Request.Method.GET, RepositoryConstants.STUDENT_ENDPOINT + "going/" + AppUser.getInstance().getGoogleId(), null, response -> {
            Type type = new TypeToken<ArrayList<Event>>() {
            }.getType();
            List<Event> events = new Gson().fromJson(response.toString(), type);
            // would do this with streams but Android doesn't seem to like them :/
            List<ChatWindow> windows = new ArrayList<>();
            for (Event event : events) {
                windows.add(adapt(event));
            }

            currentWindows.clear();
            currentWindows.addAll(windows);

            adapter.notifyDataSetChanged();

        }, Throwable::printStackTrace));
    }

    public ChatWindow adapt(Event event) {
        return new ChatWindow(event.getName(), "<LAST MESSAGE WOULD GO HERE - NOT POSSIBLE WITH CURRENT IMPLEMENTATION>");
    }

    public void onClickPersonalProfileButton() {
        Intent intent = new Intent(this, OrganiserProfileActivity.class);
        startActivity(intent);
    }

    public void onClickHomeButton() {
        Intent intent = new Intent(this, HomePageActivity.class);
        startActivity(intent);
    }

    public void onClickLikedEventsButton() {
        Intent intent = new Intent(this, LikedEventsActivity.class);
        startActivity(intent);
    }
}