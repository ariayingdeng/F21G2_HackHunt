package com.example.f21g2_hackhunt.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.f21g2_hackhunt.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class UserPostsActivity extends MainActivity {
    LinearLayout layoutPosts;
    String currentUsername;
    TextView txtViewUsername;
    protected static Bitmap currentBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_posts);

        txtViewUsername = findViewById(R.id.txtViewUsername);
        layoutPosts = findViewById(R.id.layoutPosts);


        if (ParseUser.getCurrentUser() != null) {
            currentUsername = ParseUser.getCurrentUser().getUsername();
            String title = currentUsername + "\'s Page";
            txtViewUsername.setText(title);
            getUserPosts();
        }
        else {
            startActivity(new Intent(UserPostsActivity.this, LoginActivity.class));
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

    private void getUserPosts() {
        ParseQuery<ParseObject> query = new ParseQuery("Post");
        query.whereContains("username", currentUsername);
        query.orderByDescending("timestamp");

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
                                    View post = getLayoutInflater().inflate(R.layout.layout_post, null, false);
                                    post.setLayoutParams(new ViewGroup.LayoutParams(
                                            ViewGroup.LayoutParams.MATCH_PARENT, 500
                                    ));
                                    post.setPadding(0,10,0,10);
                                    ImageView imagePost = post.findViewById(R.id.imgViewPost);
                                    TextView txtViewDate = post.findViewById(R.id.txtViewDate);
                                    TextView txtViewCaption = post.findViewById(R.id.txtViewCaption);
                                    TextView txtViewDelete = post.findViewById(R.id.deletePost);
                                    TextView txtViewEdit = post.findViewById(R.id.editPost);
                                    EditText editTxtCaption = post.findViewById(R.id.editTxtCaption);
                                    TextView txtViewSave = post.findViewById(R.id.savePost);

                                    String postId = object.getObjectId();

                                    txtViewDelete.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            deleteComments(postId);
                                            object.deleteInBackground();
                                            Toast.makeText(UserPostsActivity.this,"Your post has been deleted successfully!", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(UserPostsActivity.this, UserPostsActivity.class));
                                        }
                                    });

                                    txtViewEdit.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            editTxtCaption.setVisibility(View.VISIBLE);
                                            txtViewSave.setVisibility(View.VISIBLE);
                                        }
                                    });

                                    txtViewSave.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            String newCaption = editTxtCaption.getText().toString();
                                            txtViewCaption.setText(newCaption);
                                            editPostCaption(postId, newCaption);
                                            Toast.makeText(UserPostsActivity.this,"Your post has been updated successfully!", Toast.LENGTH_SHORT).show();
                                            editTxtCaption.setVisibility(View.INVISIBLE);
                                            txtViewSave.setVisibility(View.INVISIBLE);
                                        }
                                    });

                                    String caption = (String) object.get("caption");
                                    String date = (String) object.get("timestamp");
                                    String simpleDate = date.substring(0, 5);
                                    imagePost.setImageBitmap(bitmap);
                                    txtViewDate.setText(simpleDate);
                                    txtViewCaption.setText(caption);
                                    layoutPosts.addView(post);

                                    post.setClickable(true);

                                    post.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                            Log.i("Click", date);
                                            currentBitmap = bitmap;
                                            Intent myPost = new Intent(UserPostsActivity.this, ViewPostActivity.class);
                                            Bundle bundle = new Bundle();
                                            bundle.putString("DATE", date);
                                            bundle.putString("CAPTION", caption);
                                            bundle.putString("postId", postId);
                                            myPost.putExtras(bundle);
                                            startActivity(myPost);
                                        }
                                    });
                                }
                            }
                        });
                    }
                }
            }
        });
    }

    public void deleteComments(String postId) {
        ParseQuery<ParseObject> query = new ParseQuery("Comment");
        query.whereEqualTo("postId",postId);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    for (ParseObject object : objects) {
                        object.deleteInBackground();
                    }
                }
            }
        });
    }

    public void editPostCaption(String postId, String newCaption) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Post");
        query.getInBackground(postId, new GetCallback<ParseObject>() {
            public void done(ParseObject ePost, ParseException e) {
                if (e == null) {
                    ePost.put("caption", newCaption);
                    ePost.saveInBackground();
                }
            }
        });
    }

}