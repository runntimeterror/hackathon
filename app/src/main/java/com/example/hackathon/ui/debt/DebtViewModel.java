package com.example.hackathon.ui.debt;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.hackathon.repository.AnnualGDPEntity;
import com.example.hackathon.repository.AnnualGDPRepository;
import com.example.hackathon.repository.CurrentAccountBalanceEntity;
import com.example.hackathon.repository.debt.DebtRepository;
import com.example.hackathon.repository.debt.DebtServiceEntity;
import com.example.hackathon.repository.debt.TotalReservesEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DebtViewModel extends AndroidViewModel {
    private DebtRepository debtRepository;
    //    private AnnualGDPRepository annualGDPRepository;
    private MutableLiveData<HashMap<String, Object>> searchResults;
    private final MutableLiveData<String> mText = new MutableLiveData<>();
    private ArrayList<String> checkedGraphs;

    public DebtViewModel(Application application) {
        super(application);
        mText.setValue("This is Debt fragment");

        debtRepository = new DebtRepository(application);
        //debtRepository.findDebtService(2014, 2018);
        searchResults = debtRepository.getSearchResults();
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

    public void findDebt(int startYear, int endYear) {
        Log.println(Log.INFO, "TESTINGREPOVIEWMODEL", "TestingRepoViewModel: findAnnualGDP");
        debtRepository.findDebtService(startYear, endYear);
    }

    public void insertDebt(DebtServiceEntity debtServiceEntity) {
        debtRepository.insertDebtService(debtServiceEntity);
    }

    public void findTotalReserves(int startYear, int endYear) {
        Log.println(Log.INFO, "TESTINGREPOVIEWMODEL", "TestingRepoViewModel: findAnnualGDP");
        debtRepository.findTotalService(startYear, endYear);
    }

    public void insertTotalReserves(TotalReservesEntity totalReservesEntity) {
        debtRepository.insertTotalReserves(totalReservesEntity);
    }
}