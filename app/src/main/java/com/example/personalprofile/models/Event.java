package com.example.personalprofile.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
