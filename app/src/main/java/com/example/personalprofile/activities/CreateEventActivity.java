package com.example.personalprofile.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Switch;
import android.widget.TextView;

import com.example.personalprofile.R;

import java.util.concurrent.atomic.AtomicBoolean;

public class CreateEventActivity extends AppCompatActivity {

    private TextView name;

    private TextView description;

    private TextView date;

    private TextView time;

    private AtomicBoolean isAlcoholFree;

    private AtomicBoolean isVirtual;

    private AtomicBoolean isInPerson;


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



