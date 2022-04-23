package com.example.hackathon.ui.agriculture;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AgricultureViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public AgricultureViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Agriculture fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}