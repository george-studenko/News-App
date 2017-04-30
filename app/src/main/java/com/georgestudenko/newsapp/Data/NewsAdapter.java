package com.georgestudenko.newsapp.Data;

import android.content.Context;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.georgestudenko.newsapp.Models.News;
import com.georgestudenko.newsapp.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static android.R.attr.start;

/**
 * Created by george on 30/04/2017.
 */

public class NewsAdapter  extends ArrayAdapter<News> {
    List<News> mData;
    Context mContext;

    public NewsAdapter(@NonNull Context context, @NonNull List<News> news) {
        super(context, 0, news);
        mContext = context;
        mData = news;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View currentView = convertView;
        if(currentView == null){
            currentView = LayoutInflater.from(getContext()).inflate(R.layout.news_item,parent,false);
        }
        final News currentNews = mData.get(position);
        TextView title = (TextView) currentView.findViewById(R.id.newsTitle);
        TextView section = (TextView) currentView.findViewById(R.id.section);
        TextView date = (TextView) currentView.findViewById(R.id.date);
        RelativeLayout newsRow = (RelativeLayout) currentView.findViewById(R.id.newsRow);

        String pattern = "EEE dd-MMM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        String formattedDate = simpleDateFormat.format(currentNews.getPublishedDate());

        title.setText(currentNews.getTitle());
        section.setText(currentNews.getSection());
        date.setText(formattedDate);
        final Uri newsUri = Uri.parse(currentNews.getUrl());

        newsRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW,newsUri);
                mContext.startActivity(intent);
            }
        });

        return currentView;
    }
}
