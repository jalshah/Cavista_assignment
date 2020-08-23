package com.jalpa.cavista.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory;

public class ImageViewModeFactory extends NewInstanceFactory {
    private ImageRepository repository;

    public ImageViewModeFactory(ImageRepository repository){
        this.repository = repository;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ImageViewModel(repository);
    }
}
