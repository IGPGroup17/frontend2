package com.example.personalprofile.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.personalprofile.AppUser;
import com.example.personalprofile.R;
import com.example.personalprofile.activities.meta.ObservingActivity;
import com.example.personalprofile.models.Student;
import com.example.personalprofile.repositories.meta.observer.NotificationContext;
import com.google.gson.GsonBuilder;


public class OrganiserProfileActivity extends ObservingActivity<Student> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_profile__organiser);

        findViewById(R.id.create_event).setOnClickListener(v -> openCreateEvent());

        findViewById(R.id.homebutton).setOnClickListener(v -> openHomePage());

        findViewById(R.id.remove_event).setOnClickListener(v -> openRemoveEvent());

        findViewById(R.id.read_reviews).setOnClickListener(v -> openReadReviews());

        findViewById(R.id.likedevents).setOnClickListener(v -> openLikedEvents());

        findViewById(R.id.chatbutton).setOnClickListener(v -> openChatPage());

        findViewById(R.id.delete_account).setOnClickListener(v -> openLoginPage());
    }

    public void openLoginPage() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void openHomePage() {
        Intent intent = new Intent(this, HomePageActivity.class);
        startActivity(intent);
    }

    public void openCreateEvent() {
        Intent intent = new Intent(this, CreateEventActivity.class);
        startActivity(intent);
    }

    public void openRemoveEvent() {
        Intent intent = new Intent(this, RemoveEventActivity.class);
        startActivity(intent);
    }

    public void openReadReviews() {
        AppUser.getInstance().setCurrentReviewOrganiserId(AppUser.getInstance().getStudent().getStudentId());
        Intent intent = new Intent(this, ReadReviewsActivity.class);
        startActivity(intent);
    }

    public void openLikedEvents() {

    }

    public void openChatPage() {
        Intent intent = new Intent(this, EventChatActivity.class);
        startActivity(intent);
    }

    @Override
    public void onNotification(NotificationContext<Student> notificationContext) {
        Log.d("organiser profile", new GsonBuilder().setPrettyPrinting().create().toJson(notificationContext.getData()));
    }
}

