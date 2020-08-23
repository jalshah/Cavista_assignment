package com.jalpa.cavista.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * parse the array of images from JSON
 */
public class ImagesResponse implements Parcelable {
    @SerializedName("id")
    @Expose
    public String id;

    @SerializedName("title")
    @Expose
    public String title;

    @SerializedName("images")
    @Expose
    public ArrayList<ImageItem> images;



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(title);
        parcel.writeList(images);
    }

    public static final ImagesResponse.Creator CREATOR = new Creator() {
        @Override
        public ImagesResponse createFromParcel(Parcel parcel) {
            return new ImagesResponse(parcel);
        }

        @Override
        public ImagesResponse[] newArray(int i) {
            return new ImagesResponse[i];
        }
    };
    public ImagesResponse(Parcel parcel) {
        this.id = parcel.readString();
        this.title =  parcel.readString();
        this.images = parcel.readArrayList(ImagesResponse.class.getClassLoader());
    }
}



