package com.example.personalprofile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Liked_Events extends AppCompatActivity {

    private ImageButton home_button;
    private ImageButton chat_button;
    private ImageButton profile_button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liked_events);

        home_button = findViewById(R.id.homebutton);
        home_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomePage();
            }
        });

        chat_button = (ImageButton) findViewById(R.id.chatbutton);
        chat_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openChatPage();
            }
        });

        profile_button = (ImageButton) findViewById(R.id.personalprofile);
        profile_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProfilePage();
            }
        });
    }

    public void openHomePage() {
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);
    }

    public void openChatPage() {
        Intent intent = new Intent(this, Event_Chat.class);
        startActivity(intent);
    }

    public void openProfilePage() {
        Intent intent = new Intent(this, PersonalProfile_Organiser.class);
        startActivity(intent);
    }




}