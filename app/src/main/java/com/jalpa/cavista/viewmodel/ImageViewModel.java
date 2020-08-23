package com.jalpa.cavista.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.jalpa.cavista.model.ApiResponse;

public class ImageViewModel extends ViewModel {
    private ImageRepository repository;

    public ImageViewModel(){
        if (repository!= null ){
            return;
        }
        repository = ImageRepository.getInstance();
    }

    ImageViewModel(ImageRepository repository) {
        this.repository=repository;
    }


    public MutableLiveData<ApiResponse> getFetchImages(int number, String s) {
         repository.fetchImages(number, s);
         return repository.getImages();
    }



}
