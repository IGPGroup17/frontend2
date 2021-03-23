package com.example.personalprofile.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.*;



public class ExampleEvent {
    public static Event.EventBuilder event = Event.builder()
            .eventId("Id")
            .name("eName")
            .description("Come and do stuff")
            .organiserId("sf239581")
            .goingUsersIDs(Collections.singletonList("list1"))
            .likes(4)
            .state("on")
            .scheduledTime("15/18/29999");

}

