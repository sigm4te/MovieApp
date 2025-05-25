package com.example.movieapp.mvp.model.api.dto;

import static java.util.Collections.emptyList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Search {

    private static final String STATUS_SUCCEEDED = "True";
    private static final String STATUS_FAILED = "False";

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

    public Search(List<SearchResultItem> items, String totalResults, String status, String error) {
        this.items = items;
        this.totalResults = totalResults;
        this.status = status;
        this.error = error;
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

    public static class SuccededSearch extends Search {
        public SuccededSearch(List<SearchResultItem> items) {
            super(items, String.valueOf(items.size()), STATUS_SUCCEEDED, null);
        }
    }

    public static class FailedSearch extends Search {
        public FailedSearch(String error) {
            super(emptyList(), String.valueOf(0), STATUS_FAILED, error);
        }
    }
}
