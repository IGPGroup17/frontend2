package com.example.personalprofile.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.personalprofile.AppUser;
import com.example.personalprofile.R;

public class CreateAccountActivity extends AppCompatActivity {

    private EditText username, age, gender, name;
    private String inputName, inputGender, inputUsername;
    private Integer inputAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        name = findViewById(R.id.editTextUsername);
        age = findViewById(R.id.editTextAge);
        gender = findViewById(R.id.editTextGender);
        username = findViewById(R.id.editTextPersonName);

        findViewById(R.id.create_account_next).setOnClickListener(v -> onClickNextButton());
    }

    private void onClickNextButton() {
        inputName = name.getText().toString();
        inputAge = Integer.parseInt(age.getText().toString());
        inputGender = gender.getText().toString();
        inputUsername = username.getText().toString();


        //validate inputs
        if(inputUsername.isEmpty() || inputName.isEmpty())
            Toast.makeText(CreateAccountActivity.this, "Empty data provided", Toast.LENGTH_LONG).show();
        else
            openNextPage();
    }

    public void openNextPage(){
        AppUser.getInstance().assignInitialPage(inputUsername, inputAge, inputGender, inputName);
        Intent intent = new Intent(this, CreateAccountActivity2.class);
        startActivity(intent);
    }

}