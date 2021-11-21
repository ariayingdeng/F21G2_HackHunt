package com.example.f21g2_hackhunt.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;



import com.example.f21g2_hackhunt.R;


public class ViewPostActivity extends UserPostsActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_post);

        TextView txtViewDateL = findViewById(R.id.txtViewDateL);
        ImageView imgViewPostL = findViewById(R.id.imgViewPostL);
        TextView txtViewCapL = findViewById(R.id.txtViewCapL);

        Intent intent = getIntent();
        Bundle myBundle = intent.getExtras();
        String caption = myBundle.getString("CAPTION","");
        String date = myBundle.getString("DATE","");

        imgViewPostL.setImageBitmap(UserPostsActivity.currentBitmap);
        txtViewDateL.setText(date);
        txtViewCapL.setText(caption);

    }
}