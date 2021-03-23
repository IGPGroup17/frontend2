package com.example.personalprofile.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.personalprofile.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.gson.GsonBuilder;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    private final GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build();

    private GoogleSignInClient client;

    private GoogleSignInAccount account;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.client = GoogleSignIn.getClient(this, googleSignInOptions);
        this.account = GoogleSignIn.getLastSignedInAccount(this);

        SignInButton button = findViewById(R.id.sign_in_button);

        button.setOnClickListener(listener -> onClickGoogleSignIn());
        button.setSize(SignInButton.SIZE_WIDE);
    }

    private void onClickGoogleSignIn() {
        Intent intent = client.getSignInIntent();
        startActivityForResult(intent, 9000);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 9000) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            Log.d("Login", new GsonBuilder().setPrettyPrinting().create().toJson(account));
            Log.d("Login", Objects.requireNonNull(account).getId());
        } catch (ApiException e) {
            e.printStackTrace();
        }
        Log.d("Login", "jesus christ your penis is gigantic");
    }
}
