package com.example.movieapp.utils;

import com.google.gson.GsonBuilder;

public class Converter {

    public static String toJson(Object obj) {
        return new GsonBuilder()
                .disableHtmlEscaping()
                .create()
                .toJson(obj);
    }
}
