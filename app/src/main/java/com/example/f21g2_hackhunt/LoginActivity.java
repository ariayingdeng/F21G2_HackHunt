package com.example.f21g2_hackhunt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class LoginActivity extends AppCompatActivity {
    EditText usernameEditText;
    EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);

    }

    public void clickLogin(View view) {
        if (usernameEditText.getText().toString().matches("") ||
                passwordEditText.getText().toString().matches("")) {
            Log.i("User input", "Missing username or password");
            Toast.makeText(this,"Please enter your username and password.", Toast.LENGTH_LONG).show();
        }
        else {
            ParseUser.logInInBackground(usernameEditText.getText().toString(), passwordEditText.getText().toString(), new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {
                    if (user != null) {
                        Log.i("Login", "Successful");
                    }
                    else {
                        Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    public void clickSignup(View view) {
        if (usernameEditText.getText().toString().matches("") ||
                passwordEditText.getText().toString().matches("")) {
            Log.i("User input", "Missing username or password");
            Toast.makeText(this,"Please enter your username and password.", Toast.LENGTH_LONG).show();
        }
        else {
            ParseUser user = new ParseUser();
            user.setUsername(usernameEditText.getText().toString());
            user.setPassword(passwordEditText.getText().toString());
            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        Log.i("Sign up", "Successful");
                    }
                    else {
                        Toast.makeText(LoginActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}