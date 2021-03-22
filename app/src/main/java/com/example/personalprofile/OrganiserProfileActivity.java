package com.example.personalprofile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class OrganiserProfileActivity extends AppCompatActivity {
     ImageButton homebutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_profile__organiser);
        Button button1;
        button1 = (Button) findViewById(R.id.read_reviews);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrganiserProfileActivity.this, ReadReviewsActivity.class);
                startActivity(intent);
            }

        });
        Button button2;
        button2 = (Button) findViewById(R.id.create_event);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrganiserProfileActivity.this, CreateEventActivity.class);
                startActivity(intent);
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

        Button update;
        update = (Button) findViewById(R.id.update_event);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCreateEvent();
            }
        });

        Button reviews;
        reviews = (Button) findViewById(R.id.read_reviews);
        reviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               openReadReviews();
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

        ImageButton chat;
        chat = (ImageButton) findViewById(R.id.chatbutton);
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openChatPage();
            }
        });
    }

    public void openHomePage() {
        Intent intent = new Intent(this, HomePageActivity.class);
        startActivity(intent);
    }

    public void openCreateEvent() {
        Intent intent = new Intent(this, CreateEventActivity.class);
        startActivity(intent);
    }

    public void openReadReviews() {
        Intent intent = new Intent(this, ReadReviewsActivity.class);
        startActivity(intent);
    }

    public void OpenLikedEvents() {
        Intent intent = new Intent(this, ReadReviewsActivity.class);
        startActivity(intent);
    }

    public void openChatPage() {
        Intent intent = new Intent(this, EventChatActivity.class);
        startActivity(intent);
    }
}

