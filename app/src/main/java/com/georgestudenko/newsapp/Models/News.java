package com.georgestudenko.newsapp.Models;

import java.util.Date;

/**
 * Created by george on 30/04/2017.
 */

public class News {
    private String mSection;
    private String mTitle;
    private Date mPublishedDate;
    private String mUrl;

    public News(String section, String title, Date publishedDate, String url) {
        mSection = section;
        mTitle = title;
        mPublishedDate = publishedDate;
        mUrl = url;
    }

    public String getSection() {
        return mSection;
    }

    public String getTitle() {
        return mTitle;
    }

    public Date getPublishedDate() {
        return mPublishedDate;
    }

    public String getUrl() {
        return mUrl;
    }
}
