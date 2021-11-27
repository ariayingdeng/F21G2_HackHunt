package com.example.f21g2_hackhunt.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.f21g2_hackhunt.R;
import com.example.f21g2_hackhunt.model.Comment;

import java.util.List;

public class CommentAdapterForSql extends BaseAdapter {

    List<Comment> commentList;

    public CommentAdapterForSql(List<Comment> commentList) {
        this.commentList = commentList;
    }

    @Override
    public int getCount() {
        return commentList.size();
    }

    @Override
    public Object getItem(int i) {
        return commentList.get(i);
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
        textViewCommenter.setText(commentList.get(i).getCommenter());
        textViewComment.setText(commentList.get(i).getComments());

        return view;
    }
}
