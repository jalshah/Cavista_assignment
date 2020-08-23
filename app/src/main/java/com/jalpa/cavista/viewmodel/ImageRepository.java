package com.jalpa.cavista.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.jalpa.cavista.model.ApiResponse;
import com.jalpa.cavista.model.ImagesResponse;
import com.jalpa.cavista.model.DataResponse;
import com.jalpa.cavista.network.ImageApi;
import com.jalpa.cavista.network.RetrofitService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageRepository {

    private static ImageRepository imageRepository;
    private final ImageApi imageApi;
    MutableLiveData<ApiResponse> response = new MutableLiveData<>();

    public ImageRepository() {
        imageApi = RetrofitService.createService(ImageApi.class);
    }

    public static ImageRepository getInstance(){
        if (imageRepository == null){
            imageRepository = new ImageRepository();
        }
        return imageRepository;
    }

    public void fetchImages(int number ,String queryString) {
        imageApi.getImages(number,queryString).enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                if (response.isSuccessful()){
                    Log.e("response",response+" ");
                    ImageRepository.this.response.setValue(new ApiResponse(response.body().data));
                }
            }

            @Override
            public void onFailure(Call<DataResponse> call, Throwable t) {
                Log.e("response"," failure"+ t.getMessage());
                response.postValue(new ApiResponse(t));
            }
        });
    }


    public MutableLiveData<ApiResponse> getImages() {
        return response;
    }

}
