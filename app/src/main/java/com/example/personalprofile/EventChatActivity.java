package com.example.personalprofile;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class EventChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_chat);

        findViewById(R.id.personalprofile).setOnClickListener(listener -> onClickPersonalProfileButton());
        findViewById(R.id.homebutton).setOnClickListener(listener -> onClickHomeButton());
        findViewById(R.id.likedevents).setOnClickListener(listener -> onClickLikedEventsButton());
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