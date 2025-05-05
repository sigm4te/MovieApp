package com.example.movieapp.mvp.view.movie_page;

public class MovieViewModel {

    public final String id;
    public final String name;
    public final String imageUrl;
    public final String type;
    public final String year;
    public final String country;
    public final String director;
    public final String rating;
    public final String plot;

    public MovieViewModel(String id, String name, String imageUrl, String type, String year, String country, String director, String rating, String plot) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.type = type;
        this.year = year;
        this.country = country;
        this.director = director;
        this.rating = rating;
        this.plot = plot;
    }
}
