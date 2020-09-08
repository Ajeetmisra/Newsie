package com.example.newsie;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsDataAdapter extends ArrayAdapter<News> {
    public NewsDataAdapter(@NonNull Activity context, @NonNull List<News> newsdata) {
        super(context,0, newsdata);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View lisIemView = convertView;
        if (lisIemView == null)
        {
            lisIemView = LayoutInflater.from(getContext()).inflate(R.layout.list_view_layout,parent,false);
        }
        News currentNewsValue = getItem(position);
        TextView txt = (TextView) lisIemView.findViewById(R.id.textView);
        assert currentNewsValue != null;
        txt.setText(currentNewsValue.getTitle());
        TextView txt2 =(TextView) lisIemView.findViewById(R.id.textView2);
        txt2.setText(currentNewsValue.getDescription());
        ImageView imageView = (ImageView) lisIemView.findViewById(R.id.imageView2);
        String imageUrl = currentNewsValue.getImageUrl();
        Picasso.get().load(imageUrl).into(imageView);

        return lisIemView;
    }
}
