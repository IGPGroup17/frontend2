package com.example.personalprofile.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;

import com.example.personalprofile.R;


public class StudentProfileActivity extends AppCompatActivity {
    private Button button;
    private ImageButton homebutton1;
    private ImageButton chatbut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_profile_user);

        button = findViewById(R.id.delete_account);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentProfileActivity.this, OrganiserProfileActivity.class);
                startActivity(intent);
            }

        });


        homebutton1 = findViewById(R.id.homebutton);
        homebutton1.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick (View v){
        openHomePage();
        }
        });

        ImageButton liked;
        liked = findViewById(R.id.likedevents);
        liked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenLikedEvents();
            }
        });

        chatbut = findViewById(R.id.chatbutton);
        chatbut.setOnClickListener(new View.OnClickListener() {
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

    public void OpenLikedEvents() {
        Intent intent = new Intent(this, LikedEventsActivity.class);
        startActivity(intent);
    }

    public void openChatPage() {
        Intent intent = new Intent(this, EventChatActivity.class);
        startActivity(intent);
    }


}
