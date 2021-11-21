package com.example.f21g2_hackhunt.adapters;


import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.f21g2_hackhunt.R;
import com.example.f21g2_hackhunt.model.Recommendation;

import java.util.List;

public class RecommendationAdapter extends BaseAdapter {

    List<Recommendation> recommendationList;

    public RecommendationAdapter(List<Recommendation> recommendationList) {
        this.recommendationList = recommendationList;
    }

    @Override
    public int getCount() {
        return recommendationList.size();
    }

    @Override
    public Recommendation getItem(int position) {
        return recommendationList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recommendation, parent, false);
            convertView.setLayoutParams(new ViewGroup.LayoutParams(GridView.AUTO_FIT, 700));

            ImageView imgViewPic = convertView.findViewById(R.id.imgViewPic);
            TextView txtViewDesc = convertView.findViewById(R.id.txtViewDesc);

            imgViewPic.setImageResource(recommendationList.get(position).getPic());
            txtViewDesc.setText(recommendationList.get(position).getDescription());
            txtViewDesc.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);




        }
        return convertView;
    }
}
