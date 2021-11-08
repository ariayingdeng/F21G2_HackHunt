package com.example.f21g2_hackhunt;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class HomePageActivity extends ListActivity {

    public void queryImageFromParse(){
        ParseQuery<ParseObject> imageQuery = new ParseQuery<ParseObject>("Post");
        imageQuery.orderByDescending("createdAt");
        imageQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> images, ParseException e) {
                if(e == null){
                    HomePageActivityAdapter adapter = new HomePageActivityAdapter(HomePageActivity.this, images);
                    setListAdapter(adapter);
                }else{
                    Toast.makeText(HomePageActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
                
            }
        });

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);



    }
}