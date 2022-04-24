package com.example.hackathon.ui.macroeconomic;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.hackathon.repository.AnnualGDPEntity;
import com.example.hackathon.repository.AnnualGDPRepository;

import java.util.HashMap;
import java.util.List;

public class MacroEconomicViewModel extends AndroidViewModel {
    private AnnualGDPRepository annualGDPRepository;
    private MutableLiveData<HashMap<String, List<AnnualGDPEntity>>> searchResults;
    private final MutableLiveData<String> mText = new MutableLiveData<>();

    public MacroEconomicViewModel(Application application) {
        super(application);
        mText.setValue("This is Macro Economic fragment");

        annualGDPRepository = new AnnualGDPRepository(application);
        annualGDPRepository.findAnnualGDPs(2014, 2018);
        searchResults = annualGDPRepository.getSearchResults();
    }

    public LiveData<String> getText() {
        return mText;
    }


    public MutableLiveData<HashMap<String, List<AnnualGDPEntity>>> getSearchResults() {
        Log.println(Log.INFO, "TESTINGREPOVIEWMODEL", "TestingRepoViewModel: getSearchResults");
        return searchResults;
    }

    public void findAnnualGDPs(int startYear, int endYear) {
        Log.println(Log.INFO, "TESTINGREPOVIEWMODEL", "TestingRepoViewModel: findAnnualGDP");
        annualGDPRepository.findAnnualGDPs(startYear, endYear);
    }

    public void insertGDPs(AnnualGDPEntity annualGDPEntity) {
        annualGDPRepository.insertAnnualGDPs(annualGDPEntity);
    }
}