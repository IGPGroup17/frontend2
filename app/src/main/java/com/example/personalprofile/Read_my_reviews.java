package com.example.personalprofile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Read_my_reviews extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_my_reviews);

        ImageButton homebutton;
        homebutton = (ImageButton) findViewById(R.id.homebutton);
        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomePage();
            }
        });

        ImageButton chatbutton;
        homebutton = (ImageButton) findViewById(R.id.chatbutton);
        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openChatPage();
            }
        });

        ImageButton personalprofilebutton;
        personalprofilebutton = (ImageButton) findViewById(R.id.personalprofile);
        personalprofilebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openPersonalProfile();
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

    public void openPersonalProfile() {
        Intent intent = new Intent(this, PersonalProfile_Organiser.class);
        startActivity(intent);
    }
}