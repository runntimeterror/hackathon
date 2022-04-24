package com.example.hackathon.ui.agriculture;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.hackathon.repository.AnnualGDPEntity;
import com.example.hackathon.repository.AnnualGDPRepository;
import com.example.hackathon.repository.CurrentAccountBalanceEntity;
import com.example.hackathon.repository.aggriculture.AggricultureRepository;
import com.example.hackathon.repository.aggriculture.FertilizerEntity;
import com.example.hackathon.repository.aggriculture.ValueAddDao;
import com.example.hackathon.repository.aggriculture.ValueAddEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AgricultureViewModel extends AndroidViewModel {

    private AggricultureRepository aggricultureRepository;
    //    private AnnualGDPRepository annualGDPRepository;
    private MutableLiveData<HashMap<String, List<ValueAddEntity>>> searchResults;
    private final MutableLiveData<String> mText = new MutableLiveData<>();
    private ArrayList<String> checkedGraphs;


    public AgricultureViewModel(Application application) {
        super(application);
        mText.setValue("This is Agriculture fragment");
        aggricultureRepository = new AggricultureRepository(application);
        aggricultureRepository.findValueAdd(2014, 2018);
        searchResults = aggricultureRepository.getSearchResults();
    }

    public LiveData<String> getText() {
        return mText;
    }

    public MutableLiveData<HashMap<String, List<ValueAddEntity>>> getSearchResults() {
        Log.println(Log.INFO, "TESTINGREPOVIEWMODEL", "TestingRepoViewModel: getSearchResults");
        return searchResults;
    }

    public void findValueAdd(int startYear, int endYear) {
        Log.println(Log.INFO, "TESTINGREPOVIEWMODEL", "TestingRepoViewModel: findAnnualGDP");
        aggricultureRepository.findValueAdd(startYear, endYear);
    }

    public void insertValueAdd(ValueAddEntity valueAddEntity) {
        aggricultureRepository.insertValueAdd(valueAddEntity);
    }


    public void findFertilizer(int startYear, int endYear) {
        Log.println(Log.INFO, "TESTINGREPOVIEWMODEL", "TestingRepoViewModel: findAnnualGDP");
        aggricultureRepository.findFertilizer(startYear, endYear);
    }

    public void insertFertilizer(FertilizerEntity fertilizerEntity) {
        aggricultureRepository.insertFertilizer(fertilizerEntity);
    }

    public void setCheckedGraphs(ArrayList<String> checkedGraphs) {
        this.checkedGraphs = checkedGraphs;
    }


    public ArrayList<String> getCheckedGraphs() {
        return checkedGraphs;
    }
}