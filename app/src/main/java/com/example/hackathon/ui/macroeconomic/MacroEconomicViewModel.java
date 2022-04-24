package com.example.hackathon.ui.macroeconomic;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.hackathon.repository.MacroEconomicsEntity;
import com.example.hackathon.repository.MacroEconomicsRepository;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;

public class MacroEconomicViewModel extends AndroidViewModel {
    private MacroEconomicsRepository macroEconomicsRepository;
    private MutableLiveData<HashMap<String, List<MacroEconomicsEntity>>> searchResults;
    private final MutableLiveData<String> mText = new MutableLiveData<>();
    private ArrayList<String> checkedGraphs;


    public MacroEconomicViewModel(Application application) {
        super(application);
        mText.setValue("This is Macro Economic fragment");

        macroEconomicsRepository = new MacroEconomicsRepository(application);
        macroEconomicsRepository.findAnnualGDPs(2014, 2018);
        searchResults = macroEconomicsRepository.getSearchResults();
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void setCheckedGraphs(ArrayList<String> checkedGraphs) {
        this.checkedGraphs = checkedGraphs;
    }

    public ArrayList<String> getCheckedGraphs() {
        return checkedGraphs;
    }


    public MutableLiveData<HashMap<String, List<MacroEconomicsEntity>>> getSearchResults() {
        Log.println(Log.INFO, "TESTINGREPOVIEWMODEL", "TestingRepoViewModel: getSearchResults");
        return searchResults;
    }

    public void findAnnualGDPs(int startYear, int endYear) {
        Log.println(Log.INFO, "TESTINGREPOVIEWMODEL", "TestingRepoViewModel: findAnnualGDP");
        macroEconomicsRepository.findAnnualGDPs(startYear, endYear);
    }

    public void insertGDPs(MacroEconomicsEntity macroEconomicsEntity) {
        macroEconomicsRepository.insertAnnualGDPs(macroEconomicsEntity);
    }
}