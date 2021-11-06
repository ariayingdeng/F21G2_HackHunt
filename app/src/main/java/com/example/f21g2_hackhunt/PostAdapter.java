package com.example.f21g2_hackhunt;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PostAdapter extends BaseAdapter {
    ArrayList<Post> userPosts;

    public PostAdapter(ArrayList<Post> userPosts) {
        this.userPosts = userPosts;
    }

    @Override
    public int getCount() {
        return userPosts.size();
    }

    @Override
    public Post getItem(int position) {
        return userPosts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout_post, parent, false);
        }
        TextView txtViewDate = convertView.findViewById(R.id.txtViewDate);
        ImageView imgViewPost = convertView.findViewById(R.id.imgViewPost);
        TextView txtViewCaption = convertView.findViewById(R.id.txtViewCaption);

        txtViewDate.setText(userPosts.get(position).getDate());
        imgViewPost.setImageResource(userPosts.get(position).getImgPic());
        txtViewCaption.setText(userPosts.get(position).getCaption());

        return convertView;
    }
}
