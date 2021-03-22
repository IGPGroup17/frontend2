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
public class Student {

    private String username;

    private String email;

    private String realName;

    private Integer age;

    private String gender;

    private String universityName;

    private String universityEmail;

    private String year;

    private String course;
}
