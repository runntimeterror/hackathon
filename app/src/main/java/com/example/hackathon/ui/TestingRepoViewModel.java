package com.example.hackathon.ui;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.hackathon.repository.AnnualGDPEntity;
import com.example.hackathon.repository.AnnualGDPRepository;

import java.util.List;

public class TestingRepoViewModel extends AndroidViewModel {
    private AnnualGDPRepository annualGDPRepository;
    private MutableLiveData<List<AnnualGDPEntity>> searchResults;

    public TestingRepoViewModel(Application application) {
        super(application);
        annualGDPRepository = new AnnualGDPRepository(application);
        annualGDPRepository.findAnnualGDPs(2014, 2018);
        searchResults = annualGDPRepository.getSearchResults();
    }

    public MutableLiveData<List<AnnualGDPEntity>> getSearchResults() {
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
