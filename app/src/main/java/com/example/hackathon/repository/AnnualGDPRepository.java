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
//    private final MutableLiveData<List<AnnualGDPEntity>> searchResults = new MutableLiveData<>();
    private final MutableLiveData<HashMap<String, List<AnnualGDPEntity>>> searchResults = new MutableLiveData<>();
    private List<AnnualGDPEntity> results;
    private HashMap<String, List<AnnualGDPEntity>> aggregatedData = new HashMap<>();

    public AnnualGDPRepository(Application application) {
        AppRoomDatabase db;
        db = AppRoomDatabase.getDatabase(application);
        annualGDPDao = db.annualGDPDao();
    }

    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
//            Country.getInstance();
            Log.println(Log.INFO, "TESTINGREPOVIEWMODEL", "AnnualGDPRepository: handler.handleMessage");
//            searchResults.setValue(results);
            String graphType = (String) msg.obj;
            aggregatedData.put(graphType, results);

            // TODO: REMOVE HARDCODED SECOND GRAPH
            List<AnnualGDPEntity> hardcodedSecondGraph = new ArrayList<>();
            for (AnnualGDPEntity gdpEntity : results) {
                AnnualGDPEntity hardcoded = new AnnualGDPEntity();
                hardcoded.setYear(gdpEntity.getYear());
                hardcoded.setIndiaGDP(gdpEntity.getChinaGDP());
                hardcoded.setChinaGDP(gdpEntity.getUsaGDP());
                hardcoded.setUsaGDP(gdpEntity.getIndiaGDP());
                hardcodedSecondGraph.add(hardcoded);
            }

            aggregatedData.put("meg1", hardcodedSecondGraph);
            searchResults.setValue(aggregatedData);
        }
    };

    public void findAnnualGDPs(int startYear, int endYear) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            Log.println(Log.INFO, "TESTINGREPOVIEWMODEL", "AnnualGDPRepository: findAnnualGDP -- prequery");
            results = annualGDPDao.findAnnualGDP(startYear, endYear);
            Log.println(Log.INFO, "TESTINGREPOVIEWMODEL", "AnnualGDPRepository: findAnnualGDP -- postquery");
//            handler.sendEmptyMessage(0);
            String graphType = "meg2";
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

//    public MutableLiveData<List<AnnualGDPEntity>> getSearchResults() {
//        Log.println(Log.INFO, "TESTINGREPOVIEWMODEL", "AnnualGDPRepository: getSearchResults");
//        return searchResults;
//    }

    public MutableLiveData<HashMap<String, List<AnnualGDPEntity>>> getSearchResults(){
        return searchResults;
    }

}
