package com.example.cookidoo.ui.explore.parasi;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ParaSiViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ParaSiViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Para Si fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}