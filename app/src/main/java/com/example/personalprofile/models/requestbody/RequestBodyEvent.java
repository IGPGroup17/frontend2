package com.example.personalprofile.models.requestbody;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RequestBodyEvent {

    private String name;

    private String description;

    private String organiserId;

    private String scheduledTime;

    private boolean isVirtual;

    private boolean isInPerson;

    private boolean isAlcoholFree;
}
