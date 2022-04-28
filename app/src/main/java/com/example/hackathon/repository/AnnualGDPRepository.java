package com.example.hackathon.repository;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.hackathon.state.Country;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AnnualGDPRepository {
    private final AnnualGDPDao annualGDPDao;
    private final CurrentAccountBalanceDao currentAccountBalanceDao;
//    private final MutableLiveData<List<AnnualGDPEntity>> searchResults = new MutableLiveData<>();
    private final MutableLiveData<HashMap<String, Object>> searchResults = new MutableLiveData<>();
    private List<AnnualGDPEntity> results;
    private List<CurrentAccountBalanceEntity> resultsCurrentAccountBalanceEntity;
    private HashMap<String, Object> aggregatedData = new HashMap<>();

    public AnnualGDPRepository(Application application) {
        AppRoomDatabase db;
        db = AppRoomDatabase.getDatabase(application);
        annualGDPDao = db.annualGDPDao();
        currentAccountBalanceDao = db.currentAccountBalanceDao();
    }

    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            Log.println(Log.INFO, "TESTINGREPOVIEWMODEL", "AnnualGDPRepository: handler.handleMessage");
            String graphType = (String) msg.obj;
            if (graphType == "MEG1"){
                aggregatedData.put(graphType, resultsCurrentAccountBalanceEntity);
            }
            if (graphType == "MEG2"){
                aggregatedData.put(graphType, results);

            }

            searchResults.setValue(aggregatedData);
        }
    };

    public void findAnnualGDPs(int startYear, int endYear) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            Log.println(Log.INFO, "TESTINGREPOVIEWMODEL", "AnnualGDPRepository: findAnnualGDP -- prequery");
            results = annualGDPDao.findAnnualGDP(startYear, endYear);
            Log.println(Log.INFO, "TESTINGREPOVIEWMODEL", "AnnualGDPRepository: findAnnualGDP -- postquery");
            String graphType = "MEG2";
            Message msg = Message.obtain();
            msg.obj = graphType;
            msg.setTarget(handler);
            msg.sendToTarget();
        });
        executor.shutdown();
    }

    public void insertAnnualGDPs(AnnualGDPEntity annualGDPEntity) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            Log.println(Log.INFO, "TESTINGREPOVIEWMODEL", "AnnualGDPRepository: insertAnnualGDP -- prequery");
            annualGDPDao.insertAnnualGDP(annualGDPEntity);
            Log.println(Log.INFO, "TESTINGREPOVIEWMODEL", "AnnualGDPRepository: insertAnnualGDP -- postquery");
//            handler.sendEmptyMessage(0);
        });
        executor.shutdown();
    }

    public void insertCurrentAccountBalances(CurrentAccountBalanceEntity currentAccountBalanceEntity) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            Log.println(Log.INFO, "TESTINGREPOVIEWMODEL", "AnnualGDPRepository: insertAnnualGDP -- prequery");
            currentAccountBalanceDao.insertCurrentAccountBalance(currentAccountBalanceEntity);
            Log.println(Log.INFO, "TESTINGREPOVIEWMODEL", "AnnualGDPRepository: insertAnnualGDP -- postquery");
            handler.sendEmptyMessage(0);
        });
        executor.shutdown();
    }

    public void findTotalCurrentBalance(int startYear, int endYear) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            Log.println(Log.INFO, "TESTINGREPOVIEWMODEL", "AnnualGDPRepository: findAnnualGDP -- prequery");
            resultsCurrentAccountBalanceEntity = currentAccountBalanceDao.findCurrentAccountBalance(startYear, endYear);
            Log.println(Log.INFO, "TESTINGREPOVIEWMODEL", "AnnualGDPRepository: findAnnualGDP -- postquery");
            String graphType = "MEG1";
            Message msg = Message.obtain();
            msg.obj = graphType;
            msg.setTarget(handler);
            msg.sendToTarget();
        });
        executor.shutdown();
    }

//    public MutableLiveData<List<AnnualGDPEntity>> getSearchResults() {
//        Log.println(Log.INFO, "TESTINGREPOVIEWMODEL", "AnnualGDPRepository: getSearchResults");
//        return searchResults;
//    }

    public MutableLiveData<HashMap<String, Object>> getSearchResults(){
        return searchResults;
    }

}
