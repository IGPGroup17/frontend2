package com.example.personalprofile.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.personalprofile.AppUser;
import com.example.personalprofile.R;

public class CreateAccountActivity2 extends AppCompatActivity {

    private EditText university, year, course, uniEmail;
    private String inputUniversity, inputCourse, inputUniEmail;
    private Integer inputYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account_2);

        university = findViewById(R.id.txtUniversity);
        year = findViewById(R.id.txtYear);
        course = findViewById(R.id.txtCourse);
        uniEmail = findViewById(R.id.txtUniEmail);

        findViewById(R.id.create_account).setOnClickListener(v -> onClickCreateAccountButton());
    }

    private void onClickCreateAccountButton() {
        inputUniversity = university.getText().toString();
        inputYear = Integer.parseInt(year.getText().toString());
        inputCourse = course.getText().toString();
        inputUniEmail = uniEmail.getText().toString();


        //validate inputs
        if(inputUniversity.isEmpty() || inputUniEmail.isEmpty())
            Toast.makeText(CreateAccountActivity2.this, "Empty data provided", Toast.LENGTH_LONG).show();
        else
            openHomePage();

    }

    public void openHomePage() {
        AppUser.getInstance().assignUniPage(inputUniversity, inputUniEmail, inputYear, inputCourse);
        Intent intent = new Intent(this, HomePageActivity.class);
        startActivity(intent);
    }

}
