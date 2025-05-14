package com.example.movieapp.mvp.model.database;

import androidx.room.TypeConverter;

import java.util.Arrays;
import java.util.List;

/**
 * Конвертёр типов для базы данных
 */
public class DatabaseTypeConverter {

    @TypeConverter
    public List<String> toList(String data) {
        return Arrays.asList(data.split(","));
    }

    @TypeConverter
    public String fromList(List<String> items) {
        return String.join(",", items);
    }
}
