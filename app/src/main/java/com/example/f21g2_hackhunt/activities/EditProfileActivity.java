package com.example.f21g2_hackhunt.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.f21g2_hackhunt.R;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class EditProfileActivity extends AppCompatActivity {

    ImageView close;
    TextView save;
    AppCompatEditText username;
    AppCompatEditText password;
    AppCompatEditText email;
    String currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        close =findViewById(R.id.close);
        save = findViewById(R.id.save);
        username = findViewById(R.id.userNameEdit);
        password = findViewById(R.id.passwordEdit);
        email = findViewById(R.id.emailEdit);
        currentUser = ParseUser.getCurrentUser().getUsername();

    }

    public void clickSave(View view) {
        if (username.getText().toString().matches("") ||
                password.getText().toString().matches("") ||
                    email.getText().toString().matches("")) {
            Log.i("User input", "Missing username, password or email");
            Toast.makeText(this,"Please input valid entries.", Toast.LENGTH_LONG).show();
        }
        else {
            ParseQuery<ParseObject> query = new ParseQuery("User");
            query.getInBackground(currentUser, new GetCallback<ParseObject>() {
                @Override
                public void done(ParseObject object, ParseException e) {
                    if (e == null) {
                        ParseUser user = new ParseUser();
                        user.put("username", username.getText().toString());
                        user.put("password", password.getText().toString());
                        user.put("email", email.getText().toString());
                        user.saveInBackground();
                        Log.i("Edit Profile", "Successful");
                        Toast.makeText(EditProfileActivity.this, "Profile is successfully updated!", Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(EditProfileActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }

                }

            });
        }

    }
    public void clickClose(View view) {
        Intent intent = new Intent(EditProfileActivity.this, MainActivity.class);
        startActivity(intent);
    }
}