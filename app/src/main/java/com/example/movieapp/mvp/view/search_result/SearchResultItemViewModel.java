package com.example.movieapp.mvp.view.search_result;

public class SearchResultItemViewModel {

    public final String id;
    public final String title;
    public final String type;
    public final String year;
    public final String imageUrl;

    public SearchResultItemViewModel(String id, String title, String type, String year, String imageUrl) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.year = year;
        this.imageUrl = imageUrl;
    }
}
