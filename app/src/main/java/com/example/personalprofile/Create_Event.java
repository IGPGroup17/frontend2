package com.example.personalprofile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Create_Event extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__event);

        Button saving;
        saving = (Button) findViewById(R.id.save);
        saving.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenPersonalProfile();
            }
        });

        Button discarding;
        discarding = (Button) findViewById(R.id.discard);
        discarding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenPersonalProfile();
            }
        });

        ImageButton liked;
        liked = (ImageButton) findViewById(R.id.likedevents);
        liked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenLikedEvents();
            }
        });

    }

    public void OpenPersonalProfile() {
        Intent intent = new Intent(this, PersonalProfile_Organiser.class);
        startActivity(intent);
    }

    public void OpenLikedEvents() {
        Intent intent = new Intent(this, Liked_Events.class);
        startActivity(intent);
    }
}



