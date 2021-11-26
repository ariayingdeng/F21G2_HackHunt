package com.example.f21g2_hackhunt.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.f21g2_hackhunt.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class HomeActivity extends MainActivity {
    LinearLayout layoutHome;
    protected static Bitmap currentBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        layoutHome = findViewById(R.id.layoutHome);

        if (ParseUser.getCurrentUser() != null) {
            getHomePosts();
        }
        else {
            startActivity(new Intent(HomeActivity.this, LoginActivity.class));
        }

        BottomNavigationView bottomNavigationView;
        bottomNavigationView = findViewById(R.id.bottomNav5);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        return true;
                    case R.id.recommendation:
                        startActivity(new Intent(getApplicationContext(), RecommendationActivity.class));
                        return true;
                    case R.id.myPost:
                        startActivity(new Intent(getApplicationContext(), UserPostsActivity.class));
                        return true;
                    case R.id.newPost:
                        startActivity(new Intent(getApplicationContext(), NewPostActivity.class));
                        return true;
                    default:
                        return false;
                }
            }
        });
    }

    private void getHomePosts() {
        ParseQuery<ParseObject> query = new ParseQuery("Post");
        query.orderByAscending("updatedAt");

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (objects.size() > 0 && e == null) {
                    for (ParseObject object: objects) {
                        ParseFile imageFile = (ParseFile) object.get("image");
                        imageFile.getDataInBackground(new GetDataCallback() {
                            @Override
                            public void done(byte[] data, ParseException e) {
                                if (e == null && data != null) {
                                    Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                                    View post = getLayoutInflater().inflate(R.layout.post_item, null, false);
                                    ImageView imagePost = post.findViewById(R.id.post_image);
                                    TextView txtViewDate = post.findViewById(R.id.txtViewPostDate);
                                    TextView txtViewCaption = post.findViewById(R.id.txtViewUserDescription);
                                    TextView txtUsernameTop = post.findViewById(R.id.txtViewUsernameTop);
                                    TextView txtUsernameBottom = post.findViewById(R.id.txtViewUsernameBottom);
                                    String userName = (String) object.get("username");
                                    String caption = (String) object.get("caption");
                                    String date = (String) object.get("timestamp");
                                    String simpleDate = date.substring(0, 5);
                                    txtUsernameTop.setText(userName);
                                    txtUsernameBottom.setText(userName);
                                    imagePost.setImageBitmap(bitmap);
                                    txtViewDate.setText(simpleDate);
                                    txtViewCaption.setText(caption);
                                    layoutHome.addView(post);

                                }
                            }
                        });
                    }
                }
            }
        });
    }

}