package com.example.cookidoo.ui.myweek;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyWeekViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public MyWeekViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is shop list fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}