package com.example.personalprofile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Event_Chat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_chat);

        ImageButton personalprofilebutton;
        personalprofilebutton = (ImageButton) findViewById(R.id.personalprofile);
        personalprofilebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPersonalProfile();
            }

        });

        ImageButton homebutton;
        homebutton = (ImageButton) findViewById(R.id.homebutton);
        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomePage();
            }
        });

        ImageButton likebutton;
        likebutton = (ImageButton) findViewById(R.id.likedevents);
        likebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLikedEvents();
            }
        });

    }
    public void openPersonalProfile() {
        Intent intent = new Intent(this, PersonalProfile_Organiser.class);
        startActivity(intent);
    }

    public void openHomePage() {
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);
    }

    public void openLikedEvents() {
        Intent intent = new Intent(this, Liked_Events.class);
        startActivity(intent);
    }
}