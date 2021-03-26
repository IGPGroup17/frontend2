package com.example.personalprofile.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.personalprofile.CachedEvent;
import com.example.personalprofile.R;
import com.example.personalprofile.activities.meta.ObservingActivity;
import com.example.personalprofile.models.Event;
import com.example.personalprofile.models.requestbody.RequestBodyEvent;
import com.example.personalprofile.repositories.EventModificationRepository;
import com.example.personalprofile.repositories.context.EventModificationContext;
import com.example.personalprofile.repositories.meta.observer.NotificationContext;
import com.google.gson.GsonBuilder;

import java.util.concurrent.atomic.AtomicBoolean;

public class CreateEventActivity extends ObservingActivity<Event> {

    private EditText name;

    private EditText description;

    private CheckBox isAlcoholFree;

    private CheckBox isVirtual;

    private CheckBox isInPerson;

    AtomicBoolean alcoholFree = new AtomicBoolean(false);

    AtomicBoolean inPerson = new AtomicBoolean(false);

    AtomicBoolean online = new AtomicBoolean(false);

    /* final EditText edit =  (EditText) findViewById(R.id.text_xyz);
        edit.getText().tostring();*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__event);

        name = findViewById(R.id.editEventName);
        description = findViewById(R.id.editDescription);

        isAlcoholFree = findViewById(R.id.alcoholFreeCheck);
        isAlcoholFree.setOnClickListener(v -> alcoholFree.set(isAlcoholFree.isChecked()));

        isInPerson = findViewById(R.id.inPersonCheck);
        isInPerson.setOnClickListener(v -> inPerson.set(isInPerson.isChecked()));

        isVirtual = findViewById(R.id.onlineCheck);
        isVirtual.setOnClickListener(v -> online.set(isVirtual.isChecked()));


        findViewById(R.id.create_event_next).setOnClickListener(listener -> onClickNextButton());
        findViewById(R.id.discard).setOnClickListener(listener -> onClickDiscardButton());
        findViewById(R.id.likedevents).setOnClickListener(listener -> onClickLikedEventsButton());
    }

    private void onClickNextButton() {

        String eventName = name.getText().toString();
        String eventDescription = description.getText().toString();

        boolean alcohol = isAlcoholFree.isChecked();
        boolean virtual = isVirtual.isChecked();
        boolean person = isInPerson.isChecked();

        Log.d("alc", "" + alcohol);
        Log.d("vir", "" + virtual);
        Log.d("per", "" + person);


        CachedEvent event = CachedEvent.getInstance();
        event.assignInitialPage(eventName, eventDescription, alcohol, virtual, person);

        Intent intent = new Intent(this, CreateEventActivity2.class);
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

    @Override
    public void onNotification(NotificationContext<Event> notificationContext) {
        Log.d("CreateEvent", new GsonBuilder().setPrettyPrinting().create().toJson(notificationContext.getData()));
    }
}



