package com.example.proyectopdm.ui.proyectos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class proyectosViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public proyectosViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}