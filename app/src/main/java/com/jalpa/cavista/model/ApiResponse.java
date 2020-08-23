package com.jalpa.cavista.model;

import java.util.ArrayList;

/**
 * ApiResponse class is responsible to store the response
 */

public class ApiResponse {
    private DataResponse data ;
    private ArrayList images = new ArrayList<ImagesResponse>();
    private Throwable error;

    public ApiResponse(ArrayList<ImagesResponse> newImages) {
        this.images = newImages;
        this.error = null;
    }

    public ApiResponse(Throwable error) {
        this.error = error;
        this.images = null;
    }
    public ArrayList<ImagesResponse> getPosts() {
        return images;
    }

    public void setPosts(ArrayList<ImagesResponse> images) {
        this.images = images;
    }

    public Throwable getError() {
        return error;
    }

    public void setError(Throwable error) {
        this.error = error;
    }

    public DataResponse getData() {
        return data;
    }

    public void setData(DataResponse data) {
        this.data = data;
    }
}
