package com.example.personalprofile.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.example.personalprofile.AppUser;
import com.example.personalprofile.R;
import com.example.personalprofile.activities.meta.ObservingActivity;
import com.example.personalprofile.models.Event;
import com.example.personalprofile.models.requestbody.RequestBodyEvent;
import com.example.personalprofile.repositories.EventModificationRepository;
import com.example.personalprofile.repositories.StudentRepository;
import com.example.personalprofile.repositories.context.EventModificationContext;
import com.example.personalprofile.repositories.context.StudentCrudContext;
import com.example.personalprofile.repositories.meta.observer.NotificationContext;
import com.google.gson.GsonBuilder;

import java.util.concurrent.atomic.AtomicBoolean;

public class CreateEventActivity extends ObservingActivity<Event> {

    private EditText name;

    private EditText description;

    private EditText date;

    private EditText time;

    private AtomicBoolean isAlcoholFree;

    private AtomicBoolean isVirtual;

    private AtomicBoolean isInPerson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__event);

        findViewById(R.id.save).setOnClickListener(listener -> onClickSaveButton());

        findViewById(R.id.discard).setOnClickListener(listener -> onClickDiscardButton());
        findViewById(R.id.likedevents).setOnClickListener(listener -> onClickLikedEventsButton());
    }

    private void onClickSaveButton() {

        RequestBodyEvent event = RequestBodyEvent.builder()
                .name("blah")
                .organiserId(AppUser.getInstance().getGoogleId())
                .description("thingz")
                .scheduledTime("whatever man just put a time here lmao")
                .isAlcoholFree(false)
                .isVirtual(false)
                .isInPerson(false)
                .build();

        sendRequest(event);

        Intent intent = new Intent(this, OrganiserProfileActivity.class);
        startActivity(intent);
    }

    private void onClickDiscardButton() {
        Intent intent = new Intent(this, OrganiserProfileActivity.class);
        startActivity(intent);
    }

    private void onClickLikedEventsButton() {
        Intent intent = new Intent(this, LikedEventsActivity.class);
        startActivity(intent);
    }

    private void sendRequest(RequestBodyEvent event) {
        EventModificationRepository.getInstance().sendRequest(this, EventModificationContext.Create.of(event));
    }

    @Override
    public void onNotification(NotificationContext<Event> notificationContext) {
        Log.d("CreateEvent", new GsonBuilder().setPrettyPrinting().create().toJson(notificationContext.getData()));
    }
}



