package com.example.f21g2_hackhunt.activities;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.f21g2_hackhunt.R;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class EditProfileActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
    }

    public void clickSave(View view) {
       EditText password = findViewById(R.id.editTextPassword);
       EditText email = findViewById(R.id.editTextEmail);
        if (password.getText().toString().matches("") || email.getText().toString().matches("")) {
            Log.i("User input", "Missing password or email");
            Toast.makeText(this,"Please input valid entries.", Toast.LENGTH_LONG).show();
        }
        else {
            ParseUser user = ParseUser.getCurrentUser();
            user.setPassword(password.getText().toString());
            user.setEmail(email.getText().toString());
            user.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if(e==null){
                        Toast.makeText(EditProfileActivity.this, "Your personal information is successfully updated", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(EditProfileActivity.this, UserPostsActivity.class));
                    }
                }
            });
        }
    }
    public void clickClose(View view) {
        Intent intent = new Intent(EditProfileActivity.this, HomeActivity.class);
        startActivity(intent);
    }
}