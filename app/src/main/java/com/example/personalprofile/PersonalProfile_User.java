package com.example.personalprofile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.DragAndDropPermissions;
import android.view.DragEvent;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;


public class PersonalProfile_User extends AppCompatActivity {
    private Button button;
    private ImageButton homebutton1;
    private ImageButton chatbut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_profile_user);

        button = (Button) findViewById(R.id.delete_account);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonalProfile_User.this, PersonalProfile_Organiser.class);
                startActivity(intent);
            }

        });


        homebutton1 =(ImageButton) findViewById(R.id.homebutton);
        homebutton1.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick (View v){
        openHomePage();
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

        chatbut = (ImageButton) findViewById(R.id.chatbutton);
        chatbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openChatPage();
            }
        });
    }


    public void openHomePage() {
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);
    }

    public void OpenLikedEvents() {
        Intent intent = new Intent(this, Liked_Events.class);
        startActivity(intent);
    }

    public void openChatPage() {
        Intent intent = new Intent(this, Event_Chat.class);
        startActivity(intent);
    }


}
