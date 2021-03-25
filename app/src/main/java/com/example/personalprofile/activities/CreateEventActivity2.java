package com.example.personalprofile.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.Spinner;

import com.example.personalprofile.CachedEvent;
import com.example.personalprofile.R;
import com.example.personalprofile.activities.meta.ObservingActivity;
import com.example.personalprofile.models.Event;
import com.example.personalprofile.repositories.EventModificationRepository;
import com.example.personalprofile.repositories.context.EventModificationContext;
import com.example.personalprofile.repositories.meta.observer.NotificationContext;
import com.example.personalprofile.util.TimeUtil;
import com.google.gson.GsonBuilder;

import java.util.Calendar;

public class CreateEventActivity2 extends ObservingActivity<Event> {

    private CalendarView calendarView;

    private Spinner timeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__event_date);

        calendarView = findViewById(R.id.create_event_date_selection);
        calendarView.setMinDate(Calendar.getInstance().getTimeInMillis());
        calendarView.setDate(Calendar.getInstance().getTimeInMillis());

        timeSpinner = findViewById(R.id.create_event_time_slot);
        ArrayAdapter<String> timeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, TimeUtil.getTimesInIntervals());
        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timeSpinner.setAdapter(timeAdapter);

        findViewById(R.id.create_event_save).setOnClickListener(l -> onSave());
    }

    private void onSave() {
        CachedEvent.getInstance().assignSecondPage(calendarView.getDate(), timeSpinner.getSelectedItem().toString());
        EventModificationRepository.getInstance().sendRequest(this, EventModificationContext.Create.of(CachedEvent.getInstance().getEvent()));
        CachedEvent.getInstance().clear();
    }

    @Override
    public void onNotification(NotificationContext<Event> notificationContext) {
        Log.d("event", new GsonBuilder().setPrettyPrinting().create().toJson(notificationContext.getData()));
    }
}
