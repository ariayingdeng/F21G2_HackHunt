package com.example.f21g2_hackhunt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseAnalytics;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
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
                // to start viewing feeds activity
                return true;
            case R.id.newPost:
                Log.i("Selected", "New Post");
                startActivity(new Intent(MainActivity.this, NewPostActivity.class));
                return true;
            case R.id.yourPosts:
                Log.i("Selected", "Your Posts");
                startActivity(new Intent(MainActivity.this, UserPostsActivity.class));
                return true;
            case R.id.profile:
                Log.i("Selected", "Edit Profile");
                return true;
            default:
                return false;
        }
    }
}