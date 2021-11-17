package com.example.f21g2_hackhunt.activities;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.f21g2_hackhunt.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NewPostActivity extends MainActivity {

    ImageView imgViewPick;
    ActivityResultLauncher<Intent> activityResultLauncher;
    Button btnPost;
    EditText inputCaptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);

        imgViewPick = findViewById(R.id.imgViewPick);
        imgViewPick.setImageResource(R.drawable.ic_addicon);
        inputCaptions = findViewById(R.id.editTextCaptions);
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode()==RESULT_OK){
                    Uri uri = result.getData().getData();
                    imgViewPick.setImageURI(uri);
                }
            }
        });

        imgViewPick.setOnClickListener((View view) -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            activityResultLauncher.launch(intent);


        });

        btnPost = findViewById(R.id.btnPost);
        btnPost.setOnClickListener((View view) ->{
            String caption = inputCaptions.getText().toString();



            BitmapDrawable bitmapDrawable = (BitmapDrawable) imgViewPick.getDrawable();
            Bitmap bitmap = bitmapDrawable.getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
            byte[] byteArray = stream.toByteArray();
            ParseFile file = new ParseFile("image.png",byteArray);
            String currentUsername = ParseUser.getCurrentUser().getUsername();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy h:mm a");
            Calendar calendar = Calendar.getInstance() ;
            String date = dateFormat.format(calendar.getTime());
            ParseObject object = new ParseObject("Post");
            object.put("timestamp",date);
            object.put("username", currentUsername);
            object.put("image",file);
            object.put("caption",caption);

            object.saveInBackground(new SaveCallback(){
                @Override
                public void done(ParseException e) {
                    if(e == null){
                        Toast.makeText(NewPostActivity.this,"Your post has been shared",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(NewPostActivity.this,"Issue sharing your post",Toast.LENGTH_SHORT).show();
                    }
                }
            });
            startActivity(new Intent(NewPostActivity.this, UserPostsActivity.class));
        });

        BottomNavigationView bottomNavigationView;
        bottomNavigationView = findViewById(R.id.bottomNav3);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        return true;
                    case R.id.recommendation:
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
}