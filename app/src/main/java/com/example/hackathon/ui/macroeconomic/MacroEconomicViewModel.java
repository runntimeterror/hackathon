package com.example.hackathon.ui.macroeconomic;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.hackathon.repository.AnnualGDPEntity;
import com.example.hackathon.repository.AnnualGDPRepository;
import com.example.hackathon.repository.CurrentAccountBalanceEntity;

import java.util.ArrayList;

import java.util.HashMap;

public class MacroEconomicViewModel extends AndroidViewModel {
    private AnnualGDPRepository annualGDPRepository;
//    private AnnualGDPRepository annualGDPRepository;
    private MutableLiveData<HashMap<String, Object>> searchResults;
    private final MutableLiveData<String> mText = new MutableLiveData<>();
    private ArrayList<String> checkedGraphs;


    public MacroEconomicViewModel(Application application) {
        super(application);
        mText.setValue("This is Macro Economic fragment");

        annualGDPRepository = new AnnualGDPRepository(application);
        //annualGDPRepository.findAnnualGDPs(2014, 2018);
        searchResults = annualGDPRepository.getSearchResults();
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


    public MutableLiveData<HashMap<String, Object>> getSearchResults() {
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

    public void insertCurrentAccountBalances(CurrentAccountBalanceEntity currentAccountBalanceEntity) {
        annualGDPRepository.insertCurrentAccountBalances(currentAccountBalanceEntity);
    }

    public void findCurrentBalances(int startYear, int endYear) {
        Log.println(Log.INFO, "TESTINGREPOVIEWMODEL", "TestingRepoViewModel: findAnnualGDP");
        annualGDPRepository.findTotalCurrentBalance(startYear, endYear);
    }
}