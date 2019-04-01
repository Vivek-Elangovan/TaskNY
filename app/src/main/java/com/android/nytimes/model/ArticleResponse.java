package com.android.nytimes.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ArticleResponse implements Serializable {
    @SerializedName("status")
    private String status;
    @SerializedName("results")
    private List<ArticleResult> results = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ArticleResult> getResults() {
        return results;
    }

    public void setResults(List<ArticleResult> results) {
        this.results = results;
    }
}
