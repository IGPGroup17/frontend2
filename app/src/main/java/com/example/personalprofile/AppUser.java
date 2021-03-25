package com.example.personalprofile;

import android.app.Activity;

import com.example.personalprofile.models.requestbody.RequestBodyStudent;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.Task;

import lombok.Getter;

public class AppUser {

    private static AppUser instance;

    private final RequestBodyStudent initialSignupStudent;

    @Getter
    private final GoogleSignInAccount account;

    private final GoogleSignInClient client;

    private AppUser(GoogleSignInClient client, GoogleSignInAccount account) {
        this.client = client;
        this.account = account;
        this.initialSignupStudent = createInitialSignupStudent(account);

    }

    private RequestBodyStudent createInitialSignupStudent(GoogleSignInAccount account) {
        return RequestBodyStudent.builder()
                .studentId(account.getId())
                .email(account.getEmail())
                .build();
    }

    public void assignInitialPage(String userName, String realName, Integer age, String gender) {
        initialSignupStudent.setUsername(userName);
        initialSignupStudent.setRealName(realName);
        initialSignupStudent.setAge(age);
        initialSignupStudent.setGender(gender);
    }

    public void assignUniPage(String uniName, String uniEmail, Integer year, String course) {
        initialSignupStudent.setUniversityName(uniName);
        initialSignupStudent.setUniversityEmail(uniEmail);
        initialSignupStudent.setYear(year);
        initialSignupStudent.setCourse(course);
    }

    public static void init(GoogleSignInClient client, GoogleSignInAccount account) {
        if (instance == null) {
            instance = new AppUser(client, account);
        }
    }

    public static AppUser getInstance() {
        return instance;
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
