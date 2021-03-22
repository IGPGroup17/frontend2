package com.example.personalprofile.activities;;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.personalprofile.R;

public class CreateAccountActivity extends AppCompatActivity {

    private EditText name, surname, age, email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        name = findViewById(R.id.editTextPersonName);
        surname = findViewById(R.id.editTextPersonSurname);
        age = findViewById(R.id.editTextAge);
        email = findViewById(R.id.editTextEmailAddress);
        password = findViewById(R.id.editTextPassword);


        findViewById(R.id.create_account).setOnClickListener(v -> onClickCreateAccountButton());
    }

    private void onClickCreateAccountButton() {
        String inputName = name.getText().toString();
        String inputSurname = surname.getText().toString();
        String inputAge = age.getText().toString();
        String inputEmail = email.getText().toString();
        String inputPassword = password.getText().toString();

        AccountContext context = AccountContext.builder()
                .firstName(inputName)
                .lastName(inputSurname)
                .age(inputAge)
                .email(inputEmail)
                .password(inputPassword)
                .build();

        //validate inputs
        if(inputName.isEmpty() || inputSurname.isEmpty() || inputAge.isEmpty() || inputEmail.isEmpty() || inputPassword.isEmpty())
            Toast.makeText(CreateAccountActivity.this, "Empty data provided", Toast.LENGTH_LONG).show();
        else if (!inputEmail.contains("@bath.ac.uk"))
            Toast.makeText(CreateAccountActivity.this, "Please enter university email", Toast.LENGTH_LONG).show();
        else
            openHomePage();
    }

    public void openHomePage() {
        Intent intent = new Intent(this, HomePageActivity.class);
        startActivity(intent);
    }


    private static class AccountContext {
        private final String firstName;
        private final String lastName;
        private final String age;
        private final String email;
        private final String password;

        private AccountContext(String firstName, String lastName, String age, String email, String password) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
            this.email = email;
            this.password = password;
        }

        public static Builder builder() {
            return new Builder();
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getAge() {
            return age;
        }

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }

        private static class Builder {
            private String firstName;
            private String lastName;
            private String age;
            private String email;
            private String password;

            public Builder firstName(String firstName) {
                this.firstName = firstName;
                return this;
            }

            public Builder lastName(String lastName) {
                this.lastName = lastName;
                return this;
            }

            public Builder age(String age) {
                this.age = age;
                return this;
            }

            public Builder email(String email) {
                this.email = email;
                return this;
            }

            public Builder password(String password) {
                this.password = password;
                return this;
            }

            public AccountContext build() {
                return new AccountContext(firstName, lastName, age, email, password);
            }
        }
    }
}