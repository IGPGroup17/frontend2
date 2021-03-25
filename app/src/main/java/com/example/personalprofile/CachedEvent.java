package com.example.personalprofile;

import android.util.Log;

import com.example.personalprofile.models.requestbody.RequestBodyEvent;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import lombok.Getter;

public class CachedEvent {

    @Getter
    private RequestBodyEvent event;

    private static CachedEvent instance;

    private CachedEvent() {
        this.event = RequestBodyEvent.builder().organiserId(AppUser.getInstance().getGoogleId()).build();
    }

    public static CachedEvent getInstance() {
        if (instance == null) {
            instance = new CachedEvent();
        }
        return instance;
    }

    public void assignInitialPage(String name, String description, boolean isAlcoholFree, boolean isVirtual, boolean isInPerson) {
        event.setName(name);
        event.setDescription(description);
        event.setAlcoholFree(isAlcoholFree);
        event.setVirtual(isVirtual);
        event.setInPerson(isInPerson);
    }

    public void assignSecondPage(long date, String time) {
        event.setScheduledTime(buildTimeFrom(date, time));
    }

    private String buildTimeFrom(long date, String time) {
        Date dateObj = new Date(date);
        Format format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        String formatted = format.format(dateObj);
        Log.d("date", formatted);
        return formatted + " " + time;
    }

    public void clear() {
        this.event = RequestBodyEvent.builder().organiserId(AppUser.getInstance().getGoogleId()).build();
    }
}
