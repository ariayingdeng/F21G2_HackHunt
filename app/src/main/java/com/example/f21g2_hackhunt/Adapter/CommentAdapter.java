package com.example.f21g2_hackhunt.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.f21g2_hackhunt.R;

import java.util.List;

public class CommentAdapter extends BaseAdapter {
    List<String> Comments;
    List<String> Commenters;

    public CommentAdapter(List<String> comments, List<String> commenters) {
        Comments = comments;
        Commenters = commenters;
    }

    @Override
    public int getCount() {
        return Comments.size();
    }

    @Override
    public Object getItem(int i) {
        return Comments.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_comment,viewGroup,false);
        }
        TextView textViewCommenter = view.findViewById(R.id.textViewCommenter);
        TextView textViewComment = view.findViewById(R.id.textViewComment);

        Log.d("DEBUG6", Commenters.get(i));
        Log.d("DEBUG7", Comments.get(i));
        textViewCommenter.setText(Commenters.get(i));
        textViewComment.setText(Comments.get(i));

        return view;
    }
}
