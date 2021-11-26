package com.example.f21g2_hackhunt.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.f21g2_hackhunt.R;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class EditProfileActivity extends AppCompatActivity {

    ImageView close;
    TextView save;
    EditText username;
    EditText password;
    EditText email;
    String currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
    }

    public void clickSave(View view) {
        username = findViewById(R.id.editTextUsername);
        password = findViewById(R.id.editTextPassword);
        email = findViewById(R.id.editTextEmail);
        if (username.getText().toString().matches("") ||
                password.getText().toString().matches("") ||
                    email.getText().toString().matches("")) {
            Log.i("User input", "Missing username, password or email");
            Toast.makeText(this,"Please input valid entries.", Toast.LENGTH_LONG).show();
        }
        else {
            try {
                ParseUser user = ParseUser.getCurrentUser();
                user.setPassword(password.getText().toString());
                user.setEmail(email.getText().toString());
                user.saveInBackground();
                Toast.makeText(this, "Your personal information is successfully updated", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            } catch (Exception e) {
                Toast.makeText(EditProfileActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }

        }




    }
    public void clickClose(View view) {
        Intent intent = new Intent(EditProfileActivity.this, HomeActivity.class);
        startActivity(intent);
    }
}