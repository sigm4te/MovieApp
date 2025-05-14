package com.example.movieapp.mvp.model.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "movies",
        foreignKeys = {@ForeignKey(
                entity = SearchResultEntity.class,
                parentColumns = "id",
                childColumns = "id",
                onDelete = ForeignKey.CASCADE)})
public class MovieEntity {

    @PrimaryKey
    @NonNull
    public String id;

    public String title;

    @ColumnInfo(name = "image_url")
    public String imageUrl;

    public String type;

    public String year;

    public String country;

    public String director;

    public String rating;

    public String plot;

    public MovieEntity(@NonNull String id, String title, String imageUrl, String type, String year, String country, String director, String rating, String plot) {
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
        this.type = type;
        this.year = year;
        this.country = country;
        this.director = director;
        this.rating = rating;
        this.plot = plot;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getType() {
        return type;
    }

    public String getYear() {
        return year;
    }

    public String getCountry() {
        return country;
    }

    public String getDirector() {
        return director;
    }

    public String getRating() {
        return rating;
    }

    public String getPlot() {
        return plot;
    }
}
