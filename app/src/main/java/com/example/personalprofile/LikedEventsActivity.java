package com.example.personalprofile;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class LikedEventsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liked_events);

        findViewById(R.id.homebutton).setOnClickListener(listener -> onClickHomeButton());
        findViewById(R.id.chatbutton).setOnClickListener(listener -> onClickChatButton());
        findViewById(R.id.personalprofile).setOnClickListener(listener -> onClickPersonalProfileButton());
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