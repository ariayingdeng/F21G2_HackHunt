package com.example.f21g2_hackhunt.activities;

import android.content.Intent;
import android.os.Bundle;
//conflicts
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import android.view.MenuItem;
//
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

//
import com.example.f21g2_hackhunt.adapters.CommentAdapter;


import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;
//
import androidx.annotation.NonNull;

import com.example.f21g2_hackhunt.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class ViewPostActivity extends UserPostsActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_post);

        TextView txtViewDateL = findViewById(R.id.txtViewDateL);
        ImageView imgViewPostL = findViewById(R.id.imgViewPostL);
        TextView txtViewCapL = findViewById(R.id.txtViewCapL);
        EditText commentText = findViewById(R.id.editTextComment);
        ImageView commentPostButton = findViewById(R.id.imgViewPostComment);


        Intent intent = getIntent();
        Bundle myBundle = intent.getExtras();
        String caption = myBundle.getString("CAPTION","");
        String date = myBundle.getString("DATE","");
        String postId = myBundle.getString("postId","");

        imgViewPostL.setImageBitmap(UserPostsActivity.currentBitmap);
        txtViewDateL.setText(date);
        txtViewCapL.setText(caption);

        commentPostButton.setImageResource(R.drawable.ic_commentarrow);
        parseQuery(postId);

        commentPostButton.setOnClickListener((View view) -> {
            String commentInput = commentText.getText().toString();
            if(commentInput.equals("")){
                Toast.makeText(this, "Please Input Your Comment", Toast.LENGTH_SHORT).show();
            }
            else{
                ParseObject object = new ParseObject("Comment");
                String username = ParseUser.getCurrentUser().getUsername();
                object.put("postId",postId);
                object.put("comments", commentInput);
                object.put("commenter", username);
                object.saveInBackground(new SaveCallback(){
                    @Override
                    public void done(ParseException e) {
                        if(e == null){
                            Toast.makeText(ViewPostActivity.this,"Your comment shared",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(ViewPostActivity.this,"Issue sharing your comment",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                startActivity(new Intent(ViewPostActivity.this,UserPostsActivity.class));
            }


        });

    }

    private void parseQuery(String postId) {
        ParseQuery<ParseObject> query = new ParseQuery("Comment");
        query.whereEqualTo("postId",postId);
        query.orderByDescending("createdAt");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                List<String> CommentsList = new ArrayList<>();
                List<String> CommenterList = new ArrayList<>();
                TextView commentTitleCount = findViewById(R.id.textViewCommentTitle);
                ListView listViewComments = findViewById(R.id.listViewComments);
                if (objects.size() > 0 && e == null) {
                    for (ParseObject object : objects) {
                        String comment = (String) object.get("comments");
                        String commenter = (String) object.get("commenter");
                        CommentsList.add(comment);
                        CommenterList.add(commenter);
                    }
                }
                CommentAdapter myCommentAdapter = new CommentAdapter(CommentsList,CommenterList);
                int comCount = myCommentAdapter.getCount();
                if(comCount<=1){
                    commentTitleCount.setText(comCount+" Comment");
                }
                else{
                    commentTitleCount.setText(comCount+" Comments");
                }
                listViewComments.setAdapter(myCommentAdapter);
            }
        });

    }
}