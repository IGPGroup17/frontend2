package com.example.personalprofile.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.personalprofile.R;
import com.example.personalprofile.activities.meta.ObservingActivity;
import com.example.personalprofile.models.Student;
import com.example.personalprofile.models.requestbody.RequestBodyStudent;
import com.example.personalprofile.repositories.StudentRepository;
import com.example.personalprofile.repositories.context.StudentCrudContext;
import com.example.personalprofile.repositories.meta.observer.NotificationContext;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class OrganiserProfileActivity extends ObservingActivity<Student> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_profile__organiser);

        findViewById(R.id.create_event).setOnClickListener(v -> openCreateEvent());

        findViewById(R.id.homebutton).setOnClickListener(v -> openHomePage());

        findViewById(R.id.update_event).setOnClickListener(v -> openCreateEvent());

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

    public void openReadReviews() {
        StudentRepository repository = StudentRepository.getInstance();

        repository.sendRequest(this, StudentCrudContext.Create.of(RequestBodyStudent.builder().studentId("1").age(1).course("penis").email("more penis").gender("helicopter").realName("i love penis").universityEmail("huge penises").universityName("wow you suck penis").username("penis").year(1).build()));
    }

    public void openLikedEvents() {
        Intent intent = new Intent(this, ReadReviewsActivity.class);
        startActivity(intent);
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

