package com.example.hackathon.repository.debt;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.hackathon.repository.AppRoomDatabase;
import com.example.hackathon.repository.aggriculture.FertilizerDao;
import com.example.hackathon.repository.aggriculture.FertilizerEntity;
import com.example.hackathon.repository.aggriculture.ValueAddDao;
import com.example.hackathon.repository.aggriculture.ValueAddEntity;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DebtRepository {
    private final DebtServiceDao debtServiceDao;
    private final TotalReservesDao totalReservesDao;
    //    private CurrentAccountBalanceDao currentAccountBalanceDao;
    //    private final MutableLiveData<List<AnnualGDPEntity>> searchResults = new MutableLiveData<>();
    private final MutableLiveData<HashMap<String, List<DebtServiceEntity>>> searchResults = new MutableLiveData<>();
    private List<DebtServiceEntity> results;
    private HashMap<String, List<DebtServiceEntity>> aggregatedData = new HashMap<>();

    public DebtRepository(Application application) {
        AppRoomDatabase db;
        db = AppRoomDatabase.getDatabase(application);
        debtServiceDao = db.debtServiceDao();
        totalReservesDao = db.totalReservesDao();
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

    public void findDebtService(int startYear, int endYear) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            Log.println(Log.INFO, "TESTINGREPOVIEWMODEL", "AnnualGDPRepository: findAnnualGDP -- prequery");
            results = debtServiceDao.findDebtService(startYear, endYear);
            Log.println(Log.INFO, "TESTINGREPOVIEWMODEL", "AnnualGDPRepository: findAnnualGDP -- postquery");
            String graphType = "DG2";
            Message msg = Message.obtain();
            msg.obj = graphType;
            msg.setTarget(handler);
            msg.sendToTarget();
        });
        executor.shutdown();
    }

    public void insertDebtService(DebtServiceEntity debtServiceEntity) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            Log.println(Log.INFO, "TESTINGREPOVIEWMODEL", "AnnualGDPRepository: insertAnnualGDP -- prequery");
            debtServiceDao.insertDebtService(debtServiceEntity);
            Log.println(Log.INFO, "TESTINGREPOVIEWMODEL", "AnnualGDPRepository: insertAnnualGDP -- postquery");
//            handler.sendEmptyMessage(0);
        });
        executor.shutdown();
    }

    public void findTotalService(int startYear, int endYear) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            Log.println(Log.INFO, "TESTINGREPOVIEWMODEL", "AnnualGDPRepository: findAnnualGDP -- prequery");
            results = totalReservesDao.findTotalReserves(startYear, endYear);
            Log.println(Log.INFO, "TESTINGREPOVIEWMODEL", "AnnualGDPRepository: findAnnualGDP -- postquery");
            String graphType = "DG2";
            Message msg = Message.obtain();
            msg.obj = graphType;
            msg.setTarget(handler);
            msg.sendToTarget();
        });
        executor.shutdown();
    }

    public void insertTotalReserves(TotalReservesEntity totalReservesEntity) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            Log.println(Log.INFO, "TESTINGREPOVIEWMODEL", "AnnualGDPRepository: insertAnnualGDP -- prequery");
            totalReservesDao.insertTotalReserves(totalReservesEntity);
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

    public MutableLiveData<HashMap<String, List<DebtServiceEntity>>> getSearchResults(){
        return searchResults;
    }
}
