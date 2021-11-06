package com.example.f21g2_hackhunt;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.ParseUser;

import java.util.ArrayList;

public class UserPostsActivity extends AppCompatActivity {
    ArrayList<Post> userPosts = new ArrayList<>();
    ListView listViewPosts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_posts);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setLogo(R.drawable.hack_hunt_logo);
        actionBar.setTitle("Hack Hunt");

        AddData();

        TextView txtViewUsername = findViewById(R.id.txtViewUsername);
        listViewPosts = findViewById(R.id.listViewPosts);
        PostAdapter postAdapter = new PostAdapter(userPosts);
        listViewPosts.setAdapter(postAdapter);

        if (ParseUser.getCurrentUser() != null) {
            String currentUsername = ParseUser.getCurrentUser().getUsername();
            String title = currentUsername + "\'s Page";
            txtViewUsername.setText(title);
        }

        listViewPosts.setOnItemClickListener((AdapterView<?> parent, View view, int position, long id) -> {
            Post currentPost = userPosts.get(position);
            TextView txtViewDateL = findViewById(R.id.txtViewDateL);
            ImageView imgViewPostL = findViewById(R.id.imgViewPostL);
            TextView txtViewCaptionL = findViewById(R.id.txtViewCaptionL);
            txtViewDateL.setText(currentPost.getDate());
            imgViewPostL.setImageResource(currentPost.getImgPic());
            txtViewCaptionL.setText(currentPost.getCaption());
            txtViewDateL.setVisibility(View.VISIBLE);
            imgViewPostL.setVisibility(View.VISIBLE);
            txtViewCaptionL.setVisibility(View.VISIBLE);
            listViewPosts.setVisibility(View.INVISIBLE);
        });

    }

    private void AddData() {
        userPosts.add(new Post("11 Nov", R.drawable.breakfast, "Easy breakfast in 5 minutes!"));
    }
}