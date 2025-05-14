package com.example.movieapp.mvp.model.api.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Search {

    @SerializedName("Search")
    @Expose
    List<SearchResultItem> items;

    public Search(List<SearchResultItem> items) {
        this.items = items;
    }

    public List<SearchResultItem> getItems() {
        return items;
    }

    public void setItems(List<SearchResultItem> items) {
        this.items = items;
    }
}
