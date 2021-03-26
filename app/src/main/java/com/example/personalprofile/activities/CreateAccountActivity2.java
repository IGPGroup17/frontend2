package com.example.personalprofile.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.example.personalprofile.AppUser;
import com.example.personalprofile.R;
import com.example.personalprofile.activities.meta.ObservingActivity;
import com.example.personalprofile.models.Student;
import com.example.personalprofile.repositories.StudentRepository;
import com.example.personalprofile.repositories.context.StudentCrudContext;
import com.example.personalprofile.repositories.meta.observer.NotificationContext;
import com.example.personalprofile.util.KeyboardUtil;

public class CreateAccountActivity2 extends ObservingActivity<Student> {

    private EditText university, year, course, uniEmail;
    private String inputUniversity, inputCourse, inputUniEmail;
    private Integer inputYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account_2);

        university = findViewById(R.id.txtUniversity);
        uniEmail = findViewById(R.id.txtUniEmail);
        year = findViewById(R.id.txtYear);
        course = findViewById(R.id.txtCourse);

        findViewById(R.id.btnComplete).setOnClickListener(v -> onClickCreateAccountButton());
    }

    private void onClickCreateAccountButton() {
        inputUniversity = university.getText().toString();
        inputYear = Integer.parseInt(year.getText().toString());
        inputUniEmail = uniEmail.getText().toString();
        inputCourse = course.getText().toString();

        AppUser.getInstance().assignUniPage(inputUniversity, inputUniEmail, inputYear, inputCourse);


        //validate inputs
        if(inputUniversity.isEmpty() || inputUniEmail.isEmpty())
            Toast.makeText(CreateAccountActivity2.this, "Empty data provided", Toast.LENGTH_LONG).show();
        else
            sendRequest();
    }

    private void sendRequest() {
        StudentRepository.getInstance().sendRequest(this, StudentCrudContext.Create.of(AppUser.getInstance().getInitialSignUpStudent()));
    }

    public void openHomePage() {
        Intent intent = new Intent(this, HomePageActivity.class);
        startActivity(intent);
    }

    @Override
    public void onNotification(NotificationContext<Student> notificationContext) {
        KeyboardUtil.hideKeyboard(this);
        Log.d("createaccount2", "received notification");
        AppUser.getInstance().setStudent(notificationContext.getData());
        openHomePage();
    }
}
