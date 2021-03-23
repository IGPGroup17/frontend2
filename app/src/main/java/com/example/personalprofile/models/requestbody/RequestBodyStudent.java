package com.example.personalprofile.models.requestbody;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestBodyStudent {

    private String studentId;

    private String username;

    private String email;

    private String realName;

    private Integer age;

    private String gender;

    private String universityName;

    private String universityEmail;

    private Integer year;

    private String course;
}
