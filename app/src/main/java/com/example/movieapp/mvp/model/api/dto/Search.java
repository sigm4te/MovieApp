package com.example.movieapp.mvp.model.api.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Search {

    private static final String STATUS_SUCCEEDED = "True";
    private static final String STATUS_FAILED = "False";
    private static final String NO_CACHED_RESULT = "No cached result!";
    private static final String EMPTY_STRING = "";

    @SerializedName("Search")
    @Expose
    List<SearchResultItem> items;

    @SerializedName("totalResults")
    @Expose
    String totalResults;

    @SerializedName("Response")
    @Expose
    String status;

    @SerializedName("Error")
    @Expose
    String error;

    public Search(List<SearchResultItem> items, Boolean status) {
        this.items = items;
        this.totalResults = String.valueOf(items.size());
        this.status = (status) ? STATUS_SUCCEEDED : STATUS_FAILED;
        this.error = (status) ? EMPTY_STRING : NO_CACHED_RESULT;
    }

    public List<SearchResultItem> getItems() {
        return items;
    }

    public int getTotalResults() {
        return Integer.parseInt(totalResults);
    }

    public Boolean isSucceeded() {
        return (status.equals(STATUS_SUCCEEDED));
    }

    public String getError() {
        return error;
    }
}
