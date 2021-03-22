package com.example.personalprofile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Spinner mySpinner1 = (Spinner) findViewById(R.id.spinner1);

        ArrayAdapter<String> myAdapter1 = new ArrayAdapter<String>(HomePage.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.filter));
        myAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner1.setAdapter(myAdapter1);


        Spinner mySpinner2 = (Spinner) findViewById(R.id.spinner2);

        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<String>(HomePage.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.sort));
        myAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner2.setAdapter(myAdapter2);

        ImageButton personalprofilebutton;
        personalprofilebutton = (ImageButton) findViewById(R.id.personalprofile);
        personalprofilebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openPersonalProfile();
            }
        });


        ImageButton chat;
        chat = (ImageButton) findViewById(R.id.chatbutton);
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenChat();
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


    public void openPersonalProfile() {
        Intent intent = new Intent(this, PersonalProfile_Organiser.class);
        startActivity(intent);
    }

    public void OpenChat() {
        Intent intent = new Intent(this, Event_Chat.class);
        startActivity(intent);
    }

    public void OpenLikedEvents() {
        Intent intent = new Intent(this, Liked_Events.class);
        startActivity(intent);
    }
}


