package com.example.chirag.newsapp;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chirag.newsapp.Constants.ApiRequestConstant;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.util.Date;

public class NewsDisplayActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_display);

        if (!getIntent().hasExtra(ApiRequestConstant.LISTVIEW_EXTRA_INFO)) return;

        if (getIntent().hasExtra(ApiRequestConstant.LISTVIEW_EXTRA_HEADER))
            setTitle(getIntent().getStringExtra(ApiRequestConstant.LISTVIEW_EXTRA_HEADER));

        final NewsInfo news = (NewsInfo) getIntent().getSerializableExtra(ApiRequestConstant.LISTVIEW_EXTRA_INFO);

        ImageView image = findViewById(R.id.news_display_image);
        if (news.getmImageUrl() != null) {
            Picasso.get().load(news.getmImageUrl()).into(image);
        } else {
            image.setVisibility(View.GONE);
        }

        TextView title = findViewById(R.id.news_display_title);
        title.setText(news.getmTitle());

        TextView date = findViewById(R.id.news_display_date);
        DateConvert dateConvert = new DateConvert();
        String postTime = dateConvert.dateConverter(news.getmDate());

        date.setText(postTime);

        TextView description = findViewById(R.id.news_display_description);
        description.setText(news.getmDescription());

        Button readMore = findViewById(R.id.news_display_button);
        readMore.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = news.getmWebUrl();
                Uri webLink = Uri.parse(name);
                Intent webIntent = new Intent(Intent.ACTION_VIEW, webLink);
                startActivity(webIntent);
            }
        });
    }


}