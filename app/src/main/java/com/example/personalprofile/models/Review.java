package com.example.personalprofile.models;

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
public class Review {

    private String eventId;

    private int rating;

    private String description;

    private String creationDate;

    private String lastModificationDate;

}