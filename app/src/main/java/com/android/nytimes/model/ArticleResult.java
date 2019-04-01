package com.android.nytimes.model;

import com.google.gson.annotations.SerializedName;

public class ArticleResult {
    @SerializedName("url")
    private String url;
    @SerializedName("section")
    private String section;
    @SerializedName("byline")
    private String byline;
    @SerializedName("title")
    private String title;
    @SerializedName("published_date")
    private String publishedDate;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getByline() {
        return byline;
    }

    public void setByline(String byline) {
        this.byline = byline;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }
}
