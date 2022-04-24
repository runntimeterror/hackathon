package com.example.hackathon.repository.aggriculture;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.hackathon.repository.AnnualGDPEntity;
import com.example.hackathon.repository.AppRoomDatabase;
import com.example.hackathon.repository.CurrentAccountBalanceDao;
import com.example.hackathon.repository.CurrentAccountBalanceEntity;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AggricultureRepository {
    private final ValueAddDao valueAddDao;
    private final FertilizerDao fertilizerDao;
//    private CurrentAccountBalanceDao currentAccountBalanceDao;
    //    private final MutableLiveData<List<AnnualGDPEntity>> searchResults = new MutableLiveData<>();
    private final MutableLiveData<HashMap<String, List<ValueAddEntity>>> searchResults = new MutableLiveData<>();
    private List<ValueAddEntity> results;
    private HashMap<String, List<ValueAddEntity>> aggregatedData = new HashMap<>();

    public AggricultureRepository(Application application) {
        AppRoomDatabase db;
        db = AppRoomDatabase.getDatabase(application);
        valueAddDao = db.valueAddDao();
        fertilizerDao = db.fertilizerDao();
//        currentAccountBalanceDao = db.currentAccountBalanceDao();
    }

    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            Log.println(Log.INFO, "TESTINGREPOVIEWMODEL", "AnnualGDPRepository: handler.handleMessage");
            String graphType = (String) msg.obj;
            aggregatedData.put(graphType, results);
            searchResults.setValue(aggregatedData);
        }
    };

    public void findValueAdd(int startYear, int endYear) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            Log.println(Log.INFO, "TESTINGREPOVIEWMODEL", "AnnualGDPRepository: findAnnualGDP -- prequery");
            results = valueAddDao.findValueAdd(startYear, endYear);
            Log.println(Log.INFO, "TESTINGREPOVIEWMODEL", "AnnualGDPRepository: findAnnualGDP -- postquery");
            String graphType = "MEG2";
            Message msg = Message.obtain();
            msg.obj = graphType;
            msg.setTarget(handler);
            msg.sendToTarget();
        });
        executor.shutdown();
    }

    public void insertValueAdd(ValueAddEntity annualGDPEntity) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            Log.println(Log.INFO, "TESTINGREPOVIEWMODEL", "AnnualGDPRepository: insertAnnualGDP -- prequery");
            valueAddDao.insertValueAdd(annualGDPEntity);
            Log.println(Log.INFO, "TESTINGREPOVIEWMODEL", "AnnualGDPRepository: insertAnnualGDP -- postquery");
//            handler.sendEmptyMessage(0);
        });
        executor.shutdown();
    }

    public void findFertilizer(int startYear, int endYear) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            Log.println(Log.INFO, "TESTINGREPOVIEWMODEL", "AnnualGDPRepository: findAnnualGDP -- prequery");
            results = fertilizerDao.findFertilizer(startYear, endYear);
            Log.println(Log.INFO, "TESTINGREPOVIEWMODEL", "AnnualGDPRepository: findAnnualGDP -- postquery");
            String graphType = "MEG2";
            Message msg = Message.obtain();
            msg.obj = graphType;
            msg.setTarget(handler);
            msg.sendToTarget();
        });
        executor.shutdown();
    }

    public void insertFertilizer(FertilizerEntity fertilizerEntity) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            Log.println(Log.INFO, "TESTINGREPOVIEWMODEL", "AnnualGDPRepository: insertAnnualGDP -- prequery");
            fertilizerDao.insertFertilizer(fertilizerEntity);
            Log.println(Log.INFO, "TESTINGREPOVIEWMODEL", "AnnualGDPRepository: insertAnnualGDP -- postquery");
//            handler.sendEmptyMessage(0);
        });
        executor.shutdown();
    }

//    public void insertCurrentAccountBalances(CurrentAccountBalanceEntity currentAccountBalanceEntity) {
//        ExecutorService executor = Executors.newSingleThreadExecutor();
//        executor.submit(() -> {
//            Log.println(Log.INFO, "TESTINGREPOVIEWMODEL", "AnnualGDPRepository: insertAnnualGDP -- prequery");
//            value.insertCurrentAccountBalance(currentAccountBalanceEntity);
//            Log.println(Log.INFO, "TESTINGREPOVIEWMODEL", "AnnualGDPRepository: insertAnnualGDP -- postquery");
////            handler.sendEmptyMessage(0);
//        });
//        executor.shutdown();
//    }

//    public MutableLiveData<List<AnnualGDPEntity>> getSearchResults() {
//        Log.println(Log.INFO, "TESTINGREPOVIEWMODEL", "AnnualGDPRepository: getSearchResults");
//        return searchResults;
//    }

    public MutableLiveData<HashMap<String, List<ValueAddEntity>>> getSearchResults(){
        return searchResults;
    }

}
