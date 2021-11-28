package com.example.f21g2_hackhunt.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.f21g2_hackhunt.adapters.CommentAdapterForSql;
import com.example.f21g2_hackhunt.interfaces.CommentDao;
import com.example.f21g2_hackhunt.model.AppDatabase;
import com.example.f21g2_hackhunt.model.Comment;
import com.parse.ParseUser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import androidx.room.Room;
import com.example.f21g2_hackhunt.R;

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

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "HackHunt.db").build();
        CommentDao commentDao = db.commentDao();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try{
//                insertCSVComments(commentDao);
                if(postId != null){
                    List<Comment> commentList = commentDao.getAllCommentsForPost(postId);
                    CommentAdapterForSql commentAdapterForSql = new CommentAdapterForSql(commentList);
                    TextView commentTitleCount = findViewById(R.id.textViewCommentTitle);
                    ListView listViewComments = findViewById(R.id.listViewComments);
                    if(commentList.size()<=1){
                        commentTitleCount.setText(commentList.size() + " Comment");
                    }
                    else {
                        commentTitleCount.setText(commentList.size() + " Comments");
                    }
                    listViewComments.setAdapter(commentAdapterForSql);
                }
            }
            catch (Exception e) {
                Log.d("DBcomment", "Database exception occurred: " + e.getMessage());
            }
        });

        commentPostButton.setOnClickListener((View view) -> {
            String commentInput = commentText.getText().toString();
            String username = ParseUser.getCurrentUser().getUsername();
            Log.d("aaa1", commentInput);
            if(commentInput.equals("")){
                Toast.makeText(this, "Please Input Your Comment", Toast.LENGTH_SHORT).show();
            }
            else{

                try{
                    Log.d("aaa2", commentInput);
                    Comment myComment = new Comment(postId,commentInput,username);
                    ExecutorService executor1 = Executors.newSingleThreadExecutor();
                    executor1.execute(()-> commentDao.insertComment(myComment));
                    Toast.makeText(this, "Your comment is shared", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    Log.d("DBcomment","Database exception occurred: " + e.getMessage());
                }
                finally {
                    startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                }
            }

        });
    }

    private void insertCSVComments(CommentDao commentDao) {
        InputStream inputStream = getResources().openRawResource(R.raw.existingcomments);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String csvCommentLine = reader.readLine();
            while ((csvCommentLine = reader.readLine()) != null) {
                String[] eachCommentLine = csvCommentLine.split(",");
                Comment eachComment = new Comment(eachCommentLine[2], eachCommentLine[1], eachCommentLine[3]);
                commentDao.insertComment(eachComment);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading CSV file " + e.getMessage());
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                throw new RuntimeException("Error closing stream..." + e.getMessage());
            }
        }
    }

}
