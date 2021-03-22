package com.example.personalprofile.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.personalprofile.R;

public class MainActivity extends AppCompatActivity {

    private EditText email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.editTextEmailAddress);
        password = findViewById(R.id.editTextPassword);

        findViewById(R.id.login).setOnClickListener(v -> {

            String inputEmail = email.getText().toString();
            String inputPassword = password.getText().toString();

            //validate inputs
            if(inputEmail.isEmpty() || inputPassword.isEmpty())
                Toast.makeText(MainActivity.this, "Empty data provided", Toast.LENGTH_LONG).show();
            else
                openHomePage();
        });

        findViewById(R.id.signup).setOnClickListener(v -> openCreateAccount());
    }

    public void openHomePage() {
        Intent intent = new Intent(this, HomePageActivity.class);
        startActivity(intent);
    }

    public void openCreateAccount() {
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
    }


}

