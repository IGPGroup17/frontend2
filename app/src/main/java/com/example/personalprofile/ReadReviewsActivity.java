package com.example.personalprofile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class ReadReviewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_my_reviews);

        findViewById(R.id.homebutton).setOnClickListener(listener -> onClickHomeButton()); // home button
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