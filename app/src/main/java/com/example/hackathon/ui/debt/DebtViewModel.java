package com.example.hackathon.ui.debt;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DebtViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public DebtViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Debt fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}