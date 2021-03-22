package com.example.personalprofile;;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateAccount extends AppCompatActivity {

    private EditText name, surname, age, email, password;
    private Button createAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        name = findViewById(R.id.editTextPersonName);
        surname = findViewById(R.id.editTextPersonSurname);
        age = findViewById(R.id.editTextAge);
        email = findViewById(R.id.editTextEmailAddress);
        password = findViewById(R.id.editTextPassword);


        createAccountButton = (Button) findViewById(R.id.create_account);
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String inputName = name.getText().toString();
                String inputSurname = surname.getText().toString();
                String inputAge = age.getText().toString();
                String inputEmail = email.getText().toString();
                String inputPassword = password.getText().toString();

                //validate inputs
                if(inputName.isEmpty() || inputSurname.isEmpty() || inputAge.isEmpty() || inputEmail.isEmpty() || inputPassword.isEmpty())
                    Toast.makeText(CreateAccount.this, "Empty data provided", Toast.LENGTH_LONG).show();
                else if (!inputEmail.contains("@bath.ac.uk"))
                    Toast.makeText(CreateAccount.this, "Please enter university email", Toast.LENGTH_LONG).show();
                else
                    openHomePage();
            }
        });
    }

    public void openHomePage() {
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);
    }
}