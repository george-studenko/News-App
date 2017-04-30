package com.georgestudenko.newsapp.Utils;

import android.content.Context;

import com.georgestudenko.newsapp.Models.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by george on 30/04/2017.
 */

public class NewsParser {
    public static List<News> parseNews(String json) {
        List<News> news = new ArrayList<>();
        try {
            String title = "";
            String section = "";
            String url= "";
            Date publisheDate = null;

            JSONObject root = new JSONObject(json);
            JSONObject response = root.getJSONObject("response");
            JSONArray results = response.getJSONArray("results");

            for(int i = 0; i < results.length();i++){
                JSONObject currentNews = results.getJSONObject(i);
                title = currentNews.has("webTitle") ? currentNews.getString("webTitle") : "";
                section = currentNews.has("sectionName") ? currentNews.getString("sectionName") : "";
                url = currentNews.has("webUrl") ? currentNews.getString("webUrl") : "";

                if(currentNews.has("webPublicationDate")) {
                    SimpleDateFormat fmtTimestamp = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                    publisheDate = fmtTimestamp.parse(currentNews.getString("webPublicationDate"));
                }

                News newsItem = new News(section,title,publisheDate,url);
                if (newsItem != null) {
                    news.add(newsItem);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();

        } catch (ParseException e) {
            System.out.println("ERROR PARSING DATE!");
            e.printStackTrace();
        }

        return news;
    }
}
