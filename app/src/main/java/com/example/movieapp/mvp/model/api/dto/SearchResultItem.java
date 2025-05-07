package com.example.movieapp.mvp.model.api.dto;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.example.movieapp.utils.Converter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.reactivex.rxjava3.core.Observable;

public class SearchResultItem implements Parcelable {

    @SerializedName("imdbID")
    @Expose
    String id;

    @SerializedName("Title")
    @Expose
    String title;

    @SerializedName("Type")
    @Expose
    String type;

    @SerializedName("Year")
    @Expose
    String year;

    @SerializedName("Poster")
    @Expose
    String imageUrl;

    public SearchResultItem(String id, String title, String type, String year, String imageUrl) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.year = year;
        this.imageUrl = imageUrl;
    }

    protected SearchResultItem(Parcel in) {
        id = in.readString();
        title = in.readString();
        type = in.readString();
        year = in.readString();
        imageUrl = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(type);
        dest.writeString(year);
        dest.writeString(imageUrl);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SearchResultItem> CREATOR = new Creator<SearchResultItem>() {
        @Override
        public SearchResultItem createFromParcel(Parcel in) {
            return new SearchResultItem(in);
        }

        @Override
        public SearchResultItem[] newArray(int size) {
            return new SearchResultItem[size];
        }
    };

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

    public Observable<String> getTitleObservable() {
        return Observable.just(title);
    }

    @NonNull
    @Override
    public String toString() {
        return Converter.toJson(this);
    }
}
