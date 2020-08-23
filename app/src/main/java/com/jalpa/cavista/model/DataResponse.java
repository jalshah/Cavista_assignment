package com.jalpa.cavista.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * DataResponse is the class which parse data field from JSON
 */

public class DataResponse {
    @SerializedName("data")
    @Expose
    public ArrayList<ImagesResponse> data;

}
