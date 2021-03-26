package com.example.personalprofile.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.personalprofile.AppUser;
import com.example.personalprofile.R;
import com.example.personalprofile.activities.meta.ObservingActivity;
import com.example.personalprofile.models.Student;
import com.example.personalprofile.repositories.StudentRepository;
import com.example.personalprofile.repositories.context.StudentCrudContext;
import com.example.personalprofile.repositories.meta.observer.NotificationContext;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.gson.GsonBuilder;

import java.util.Objects;

public class LoginActivity extends ObservingActivity<Student> {

    private GoogleSignInOptions googleSignInOptions;

    private GoogleSignInClient client;

    private GoogleSignInAccount account;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

         this.googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        this.client = GoogleSignIn.getClient(this, googleSignInOptions);
        this.account = GoogleSignIn.getLastSignedInAccount(this);

        findViewById(R.id.login).setOnClickListener(l -> {
            Intent intent = new Intent(this, HomePageActivity.class);
            startActivity(intent);
        });

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
            AppUser.init(client, account);

            StudentRepository.getInstance().sendRequest(this, StudentCrudContext.Read.of(Objects.requireNonNull(account).getId()));

            Log.d("Login", Objects.requireNonNull(account).getId());
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onNotification(NotificationContext<Student> notificationContext) {
        Log.d(notificationContext.getMessage(), new GsonBuilder().setPrettyPrinting().create().toJson(notificationContext.getData()));
        Intent intent;
        if (notificationContext.getMessage().equals("NOT FOUND")) {
            intent = new Intent(this, CreateAccountActivity.class);
        } else {
            AppUser.getInstance().setStudent(notificationContext.getData());
            intent = new Intent(this, HomePageActivity.class);
        }


        startActivity(intent);
    }
}
