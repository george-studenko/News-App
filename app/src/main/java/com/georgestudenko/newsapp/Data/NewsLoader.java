package com.georgestudenko.newsapp.Data;


import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.georgestudenko.newsapp.Models.News;
import com.georgestudenko.newsapp.Utils.NetworkUtils;
import com.georgestudenko.newsapp.Utils.NewsParser;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Created by george on 30/04/2017.
 */

public class NewsLoader extends AsyncTaskLoader<List<News>> {
    Context mContext;

    public NewsLoader(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public List<News> loadInBackground() {
        URL url = NetworkUtils.buildUrl();
        List<News> list = null;
        try {
            String json = NetworkUtils.getResponseFromHttpUrl(url,mContext);

            if(json!=null && json.length()>0){
                list = NewsParser.parseNews(json);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
