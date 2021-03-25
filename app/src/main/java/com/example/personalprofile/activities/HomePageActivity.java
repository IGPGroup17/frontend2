package com.example.personalprofile.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personalprofile.R;
import com.example.personalprofile.activities.meta.ObservingActivity;
import com.example.personalprofile.models.Event;
import com.example.personalprofile.repositories.EventSearchRepository;
import com.example.personalprofile.repositories.context.EventSearchContext;
import com.example.personalprofile.repositories.eventsearch.EventSortingComparatorFactory;
import com.example.personalprofile.repositories.meta.observer.NotificationContext;
import com.example.personalprofile.util.KeyboardUtil;
import com.example.personalprofile.views.EventRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HomePageActivity extends ObservingActivity<List<Event>> {

    private Spinner sortSpinner;

    private Spinner filterSpinner;

    private EditText searchBox;

    private EventSearchRepository repository;

    private RecyclerView recyclerView;

    private EventRecyclerViewAdapter adapter;

    private List<Event> currentEvents = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        this.repository = new EventSearchRepository();

        createFilterSpinner();
        createSortSpinner();

        searchBox = findViewById(R.id.search_box);
        findViewById(R.id.home_search_button).setOnClickListener(listener -> onClickSearchButton());
        findViewById(R.id.personalprofile).setOnClickListener(listener -> onClickPersonalProfileButton());
        findViewById(R.id.chatbutton).setOnClickListener(v -> onClickChatButton());
        findViewById(R.id.likedevents).setOnClickListener(v -> onClickLikedEventsButton());

        this.recyclerView = findViewById(R.id.event_recycler_view);

        this.adapter = new EventRecyclerViewAdapter(this, currentEvents);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void createSortSpinner() {
        sortSpinner = findViewById(R.id.spinner2);

        ArrayAdapter<String> sortAdapter = new ArrayAdapter<>(HomePageActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.sort));
        sortAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortSpinner.setAdapter(sortAdapter);
    }

    private void createFilterSpinner() {
        filterSpinner = findViewById(R.id.spinner1);

        ArrayAdapter<String> filterAdapter = new ArrayAdapter<>(HomePageActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.filter));
        filterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filterSpinner.setAdapter(filterAdapter);
    }

    private void onClickSearchButton() {
        KeyboardUtil.hideKeyboard(this);
        EventSearchContext options = EventSearchContext.builder()
                .searchQuery(searchBox.getText().toString())
                .build();
        repository.sendRequest(this, options);
        KeyboardUtil.hideKeyboard(this);
    }

    public void onClickPersonalProfileButton() {
        Intent intent = new Intent(this, OrganiserProfileActivity.class);
        startActivity(intent);
    }

    public void onClickChatButton() {
        Intent intent = new Intent(this, EventChatActivity.class);
        startActivity(intent);
    }

    public void onClickLikedEventsButton() {
        Intent intent = new Intent(this, LikedEventsActivity.class);
        startActivity(intent);
    }

    @Override
    public void onNotification(NotificationContext<List<Event>> notificationContext) {
        List<Event> events = new ArrayList<>(notificationContext.getData());
        Log.d("matches", "" + events.size());

        this.currentEvents.clear();
        this.currentEvents.addAll(events);

        adapter.notifyDataSetChanged();


        Toast.makeText(HomePageActivity.this, "Matches: " + notificationContext.getData().size(), Toast.LENGTH_LONG).show();
    }

    private void sort(List<Event> events, EventSortingComparatorFactory.Strategy sortStrategy) {
        if (sortStrategy != null) {
            Comparator<Event> comparator = sortStrategy.comparator();
            if (comparator != null) {
                Collections.sort(events, comparator);
            }
        }
    }
}


