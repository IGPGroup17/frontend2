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
public class Organiser {

    private String profileId;

    private String organisationName;

    private String organisationDesc;

    private String uniEmail;

    private String email;

    private String universityName;
}
