package com.example.personalprofile.models;

import java.util.List;

import lombok.Getter;

@Getter
public class Event {

    private String name;

    private String description;

    private String organiserId;

    private List<String> goingUsersIDs;

    private List<String> interestedUsersIDs;

    private int likes;

    private String state;

    private String scheduledTime;
}
