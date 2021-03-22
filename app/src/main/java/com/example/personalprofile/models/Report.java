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
public class Report {


    private String userName;

    private String event;

    private String reportHead;

    private String reportBody;

    private String reportRating;

}
