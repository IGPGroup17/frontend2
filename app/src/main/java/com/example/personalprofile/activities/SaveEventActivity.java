package com.example.personalprofile.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.personalprofile.R;






public class SaveEventActivity extends AppCompatActivity {

    EditText editEventName, editDescription, editTextDate, editTextTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__event);

        viewInitializations();
    }

    void viewInitializations() {
        editEventName = findViewById(R.id.editEventName);
        editDescription = findViewById(R.id.editDescription);
        editTextTime = findViewById(R.id.editTextTime);
        editTextDate = findViewById(R.id.editTextDate);

        // To show back button in actionbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    // Checking if the input in form is valid
    boolean validateInput() {
        if (editEventName.getText().toString().equals("")) {
            editEventName.setError("Please Enter A Name");
            return false;
        }
        if (editDescription.getText().toString().equals("")) {
            editDescription.setError("Please Enter A Description");
            return false;
        }
        if (editTextTime.getText().toString().equals("")) {
            editTextTime.setError("Please Enter A Time");
            return false;
        }
        if (editTextDate.getText().toString().equals("")) {
            editTextDate.setError("Please Enter A Date");
            return false;
        }
        return true;
    }



    // Hook Click Event

    public void performCreateEvent (View v) {
        if (validateInput()) {

            // Input is valid, here send data to your server

            String name = editEventName.getText().toString();
            String description = editDescription.getText().toString();
            String scheduledTime = (editTextTime.getText().toString() + ", "+ editTextDate.getText().toString());


            Toast.makeText(this,"Heres your event",Toast.LENGTH_SHORT).show();
            // Here you can call you API

        }
    }

}

