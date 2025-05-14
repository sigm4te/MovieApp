package com.example.movieapp.mvp.model.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "search_result")
public class SearchResultEntity {

    @PrimaryKey
    @NonNull
    public String id;

    public String title;

    public String type;

    public String year;

    @ColumnInfo(name = "image_url")
    public String imageUrl;

    public SearchResultEntity(@NonNull String id, String title, String type, String year, String imageUrl) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.year = year;
        this.imageUrl = imageUrl;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getYear() {
        return year;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
