package com.example.f21g2_hackhunt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.parse.ParseAnalytics;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setLogo(R.drawable.hack_hunt_logo);
        actionBar.setTitle("Hack Hunt");

//        ParseObject parseObject = new ParseObject("Test");
//        parseObject.put("Firstname", "Ying");
//        parseObject.put("Lastname", "Deng");
//        parseObject.saveInBackground(new SaveCallback() {
//            @Override
//            public void done(ParseException e) {
//                if (e == null) {
//                    Log.i("Connection", "Successful");
//                }
//                else {
//                    Log.i("Connection", "Unsuccessful");
//                    e.printStackTrace();
//                }
//            }
//        });

        ParseAnalytics.trackAppOpenedInBackground(getIntent());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.homepage:
                Log.i("Selected", "Homepage");
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                return true;
            case R.id.newPost:
                Log.i("Selected", "New Post");
                startActivity(new Intent(getApplicationContext(), NewPostActivity.class));
                return true;
            case R.id.yourPosts:
                Log.i("Selected", "Your Posts");
                startActivity(new Intent(getApplicationContext(), UserPostsActivity.class));
                return true;
            case R.id.profile:
                Log.i("Selected", "Edit Profile");
                return true;
            case R.id.logout:
                Log.i("Selected", "Log Out");
                ParseUser.logOut();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                return true;
            default:
                return false;
        }
    }
}