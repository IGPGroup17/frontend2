package com.example.personalprofile.models;

import java.util.Collections;


public class ExampleEvent {
    public static Event.EventBuilder event = Event.builder()
            .eventID("Id")
            .name("eName")
            .description("Come and do stuff")
            .organiserId("sf239581")
            .goingUsersIDs(Collections.singletonList("list1"))
            .likes(4)
            .state("on")
            .scheduledTime("15/18/29999");

}

