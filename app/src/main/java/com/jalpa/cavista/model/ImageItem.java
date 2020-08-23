package com.jalpa.cavista.model;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImageItem implements Parcelable {

    @SerializedName("id")
    @Expose
    public String id;

    @SerializedName("link")
    @Expose
    public String link;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(link);
    }


    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public ImageItem createFromParcel(Parcel in) {
            return new ImageItem(in);
        }

        @Override
        public ImageItem[] newArray(int i) {
            return new ImageItem[i];
        }
    };

    public ImageItem(Parcel in){
        this.id = in.readString();
        this.link =  in.readString();
    }
}




