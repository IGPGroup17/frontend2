package com.example.personalprofile.models;

import lombok.Getter;

@Getter
public class Review {

    private String eventId;

    private int rating;

    private String description;

    private String creationDate;

    private String lastModificationDate;

}