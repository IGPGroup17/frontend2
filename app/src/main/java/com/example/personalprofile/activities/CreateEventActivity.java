package com.example.personalprofile.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.EditText;

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

    private EditText date;

    private EditText time;

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

        name = (EditText) findViewById(R.id.editEventName);
        description = (EditText) findViewById(R.id.editDescription);
        date = (EditText) findViewById(R.id.editTextDate);
        time = (EditText) findViewById(R.id.editTextTime);

        isAlcoholFree = findViewById(R.id.alcoholFreeCheck);
        isAlcoholFree.setOnClickListener(v -> alcoholFree.set(isAlcoholFree.isChecked()));

        isInPerson = findViewById(R.id.inPersonCheck);
        isInPerson.setOnClickListener(v -> inPerson.set(isInPerson.isChecked()));

        isVirtual = findViewById(R.id.onlineCheck);
        isVirtual.setOnClickListener(v -> online.set(isVirtual.isChecked()));

        findViewById(R.id.save).setOnClickListener(listener -> onClickSaveButton());

        findViewById(R.id.discard).setOnClickListener(listener -> onClickDiscardButton());
        findViewById(R.id.likedevents).setOnClickListener(listener -> onClickLikedEventsButton());
    }

    private void onClickSaveButton() {

        String eventName = name.getText().toString();
        String eventDescription = description.getText().toString();
        String eventDate = date.getText().toString();
        String eventTime = time.getText().toString();

        RequestBodyEvent event = RequestBodyEvent.builder()
                .name(eventName)
                .organiserId("yo")
                .description(eventDescription)
                .scheduledTime(eventDate + eventTime)
                .isAlcoholFree(alcoholFree.get())
                .isVirtual(online.get())
                .isInPerson(inPerson.get())
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



