package com.example.personalprofile;

import android.app.Activity;

import com.example.personalprofile.models.Student;
import com.example.personalprofile.models.requestbody.RequestBodyStudent;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.Task;

import lombok.Getter;
import lombok.Setter;

public class AppUser {

    private static AppUser instance;

    @Getter
    private final RequestBodyStudent initialSignUpStudent;

    @Getter @Setter
    private Student student;

    @Getter @Setter
    private String currentReviewOrganiserId;

    @Getter
    private final GoogleSignInAccount account;

    private final GoogleSignInClient client;

    private AppUser(GoogleSignInClient client, GoogleSignInAccount account) {
        this.client = client;
        this.account = account;
        this.initialSignUpStudent = createInitialSignupStudent(account);

    }

    private RequestBodyStudent createInitialSignupStudent(GoogleSignInAccount account) {
        return RequestBodyStudent.builder()
                .studentId(account.getId())
                .email(account.getEmail())
                .build();
    }

    public void assignInitialPage(String username, Integer age, String gender, String name) {
        initialSignUpStudent.setUsername(username);
        initialSignUpStudent.setAge(age);
        initialSignUpStudent.setGender(gender);
        initialSignUpStudent.setRealName(name);
    }

    public void assignUniPage(String uniName, String uniEmail, Integer year, String course) {
        initialSignUpStudent.setUniversityName(uniName);
        initialSignUpStudent.setUniversityEmail(uniEmail);
        initialSignUpStudent.setYear(year);
        initialSignUpStudent.setCourse(course);
    }

    public static void init(GoogleSignInClient client, GoogleSignInAccount account) {
        if (instance == null) {
            instance = new AppUser(client, account);
        }
    }

    public static AppUser getInstance() {
        return instance;
    }

    public Task<Void> revokeAccess() {
        return client.revokeAccess();
    }

    public String getGoogleId() {
        return account.getId();
    }

    public Task<Void> signOutTask() {
        return client.signOut();
    }

    public void signOut(Activity activity) {
        signOutTask().addOnCompleteListener(activity, task -> {//insert whatever you fancy here
            });
    }
}
