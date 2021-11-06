package com.example.f21g2_hackhunt;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.ByteArrayOutputStream;

public class NewPostActivity extends AppCompatActivity {

    ImageView imgViewPick;
    ActivityResultLauncher<Intent> activityResultLauncher;
    Button btnPost;
    EditText words;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setLogo(R.drawable.hack_hunt_logo);
        actionBar.setTitle("Hack Hunt");

        imgViewPick = findViewById(R.id.imgViewPick);
        imgViewPick.setImageResource(R.drawable.ic_addicon);
        words = findViewById(R.id.editTextLines);
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
            String lines = words.getText().toString();
            // parse upload Image ??
            BitmapDrawable bitmap = (BitmapDrawable) imgViewPick.getDrawable();
            Bitmap bm = bitmap.getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.PNG,100,stream);
            byte[] byteArray = stream.toByteArray();
            ParseFile file = new ParseFile("image.png",byteArray);
            String currentUsername = ParseUser.getCurrentUser().getUsername();
            ParseObject object = new ParseObject("Post");
            object.put("username", currentUsername);
            object.put("image",file);
            object.put("caption",lines);
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

            startActivity(new Intent(NewPostActivity.this,UserPostsActivity.class));
        });

    }
}