package com.example.personalprofile.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.personalprofile.R;

public class CreateEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__event);

        findViewById(R.id.save).setOnClickListener(listener -> onClickSaveButton());
        findViewById(R.id.discard).setOnClickListener(listener -> onClickDiscardButton());
        findViewById(R.id.likedevents).setOnClickListener(listener -> onClickLikedEventsButton());
    }

    private void onClickSaveButton() {
        Intent intent = new Intent(this, OrganiserProfileActivity.class);
        startActivity(intent);
    }

    private void onClickDiscardButton() {
        Intent intent = new Intent(this, OrganiserProfileActivity.class);
        startActivity(intent);
    }

    private void onClickLikedEventsButton() {
        Intent intent = new Intent(this, LikedEventsActivity.class);
        startActivity(intent);
    }
}



