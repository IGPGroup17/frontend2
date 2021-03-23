package com.example.personalprofile.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.personalprofile.R;
import com.example.personalprofile.repositories.event.ElasticSearchQueryAdapter;
import com.example.personalprofile.repositories.event.EventRepository;
import com.example.personalprofile.repositories.event.EventSearchOptions;

import org.json.JSONException;

public class HomePageActivity extends AppCompatActivity {

    private Spinner sortSpinner;
    private Spinner filterSpinner;
    private EditText searchBox;

    private EventRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        this.repository = new EventRepository();

        createFilterSpinner();
        createSortSpinner();

        searchBox = findViewById(R.id.search_box);
        findViewById(R.id.home_search_button).setOnClickListener(listener -> onClickSearchButton());
        findViewById(R.id.personalprofile).setOnClickListener(listener -> onClickPersonalProfileButton());
        findViewById(R.id.chatbutton).setOnClickListener(v -> onClickChatButton());
        findViewById(R.id.likedevents).setOnClickListener(v -> onClickLikedEventsButton());
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
        Log.d("sending request", "haha lmao you suck");
        EventSearchOptions options = EventSearchOptions.builder()
                .searchQuery(searchBox.getText().toString())
                .build();
        repository.sendRequest(options);
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
}


