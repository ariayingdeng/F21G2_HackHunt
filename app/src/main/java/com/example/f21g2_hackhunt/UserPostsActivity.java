package com.example.f21g2_hackhunt;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRole;
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
            startActivity(new Intent(UserPostsActivity.this,LoginActivity.class));
        }
    }

    private void getUserPosts() {
        ParseQuery<ParseObject> query = new ParseQuery("Post");
//        query.whereEqualTo("username", currentUsername);
        query.whereContains("username", currentUsername);
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
                                    View post = getLayoutInflater().inflate(R.layout.layout_post, null, false);
                                    post.setLayoutParams(new ViewGroup.LayoutParams(
                                            ViewGroup.LayoutParams.MATCH_PARENT, 800
                                    ));
                                    post.setPadding(0,10,0,10);
                                    ImageView imagePost = post.findViewById(R.id.imgViewPost);
                                    TextView txtViewDate = post.findViewById(R.id.txtViewDate);
                                    TextView txtViewCaption = post.findViewById(R.id.txtViewCaption);
                                    String caption = (String) object.get("caption");
                                    String date = (String) object.get("timestamp");
                                    imagePost.setImageBitmap(bitmap);
                                    txtViewDate.setText(date);
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

}