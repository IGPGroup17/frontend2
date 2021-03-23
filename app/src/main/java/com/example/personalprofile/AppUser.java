package com.example.personalprofile;

import android.app.Activity;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.Task;

public class AppUser {

    private static AppUser instance;

    private final GoogleSignInAccount account;

    private final GoogleSignInClient client;

    private AppUser(GoogleSignInClient client, GoogleSignInAccount account) {
        this.client = client;
        this.account = account;
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
