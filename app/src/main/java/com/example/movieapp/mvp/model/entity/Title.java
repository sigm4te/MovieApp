package com.example.movieapp.mvp.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.reactivex.rxjava3.core.Observable;

public class Title implements Parcelable {

    @SerializedName("imdbID")
    @Expose
    String id;

    @SerializedName("Title")
    @Expose
    String name;

    @SerializedName("Type")
    @Expose
    String type;

    @SerializedName("Year")
    @Expose
    String year;

    @SerializedName("Poster")
    @Expose
    String imageUrl;

    public Title(String id, String name, String type, String year, String imageUrl) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.year = year;
        this.imageUrl = imageUrl;
    }

    public Title(String name) {
        this.name = name;
        id = null;
        type = null;
        year = null;
        imageUrl = null;
    }

    protected Title(Parcel in) {
        id = in.readString();
        name = in.readString();
        type = in.readString();
        year = in.readString();
        imageUrl = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(type);
        dest.writeString(year);
        dest.writeString(imageUrl);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Title> CREATOR = new Creator<Title>() {
        @Override
        public Title createFromParcel(Parcel in) {
            return new Title(in);
        }

        @Override
        public Title[] newArray(int size) {
            return new Title[size];
        }
    };

    public String getId() {
        return id;
    }

    public Observable<String> getName() {
        return Observable.just(name);
    }

    public String getNameString() {
        return name;
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