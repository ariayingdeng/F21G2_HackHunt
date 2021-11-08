package com.example.f21g2_hackhunt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.parse.ParseObject;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomePageActivityAdapter extends ArrayAdapter<ParseObject> {
    Context contextList;
    List<ParseObject> userImageList;

    public HomePageActivityAdapter(Context context, List<ParseObject> images) {
        super(context, R.layout.single_row, images);
        contextList = context;
        userImageList = images;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(contextList).inflate(
                    R.layout.single_row, null);
            holder = new ViewHolder();
            holder.userImage = (ImageView) convertView.
                    findViewById(R.id.imageViewUserFeed);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ParseObject object = userImageList.get(position);

        Picasso.get().load(object.getParseFile("image").getUrl()).noFade().into(holder.userImage);
        return convertView;

    }

    public  static  class ViewHolder {
        ImageView userImage;
    }
}
