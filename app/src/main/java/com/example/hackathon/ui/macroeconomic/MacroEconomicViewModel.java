package com.example.hackathon.ui.macroeconomic;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MacroEconomicViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public MacroEconomicViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Macro Economic fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}