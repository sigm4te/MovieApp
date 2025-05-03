package com.example.movieapp.mvp.model.api.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Search {

    @SerializedName("Search")
    @Expose
    List<SearchResultItem> items;

    public List<SearchResultItem> getItems() {
        return items;
    }
}
