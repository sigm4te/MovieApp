package com.example.movieapp.mvp.model.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.movieapp.mvp.model.database.DatabaseTypeConverter;

import java.util.List;

@Entity(tableName = "search_history")
public class SearchHistoryEntity {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "search_query")
    public String query;

    @TypeConverters({DatabaseTypeConverter.class})
    public List<String> ids;

    public SearchHistoryEntity(@NonNull String query, List<String> ids) {
        this.query = query;
        this.ids = ids;
    }

    @NonNull
    public String getQuery() {
        return query;
    }

    public List<String> getIds() {
        return ids;
    }
}
