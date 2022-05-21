package com.example.cookidoo.ui.explore.temas;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TemasViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public TemasViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Destaque fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}