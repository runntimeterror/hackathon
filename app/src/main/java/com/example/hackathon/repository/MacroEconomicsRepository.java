package com.example.hackathon.repository;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MacroEconomicsRepository {
    private final MacroEconomicsDao macroEconomicsDao;
//    private final MutableLiveData<List<AnnualGDPEntity>> searchResults = new MutableLiveData<>();
    private final MutableLiveData<HashMap<String, List<MacroEconomicsEntity>>> searchResults = new MutableLiveData<>();
    private List<MacroEconomicsEntity> results;
    private HashMap<String, List<MacroEconomicsEntity>> aggregatedData = new HashMap<>();

    public MacroEconomicsRepository(Application application) {
        AppRoomDatabase db;
        db = AppRoomDatabase.getDatabase(application);
        macroEconomicsDao = db.annualGDPDao();
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
            List<MacroEconomicsEntity> hardcodedSecondGraph = new ArrayList<>();
            for (MacroEconomicsEntity gdpEntity : results) {
                MacroEconomicsEntity hardcoded = new MacroEconomicsEntity();
                hardcoded.setYear(gdpEntity.getYear());
                hardcoded.setIndiaGDP(gdpEntity.getChinaGDP());
                hardcoded.setChinaGDP(gdpEntity.getUsaGDP());
                hardcoded.setUsaGDP(gdpEntity.getIndiaGDP());
                hardcodedSecondGraph.add(hardcoded);
            }

            aggregatedData.put("MEG1", hardcodedSecondGraph);
            searchResults.setValue(aggregatedData);
        }
    };

    public void findAnnualGDPs(int startYear, int endYear) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            Log.println(Log.INFO, "TESTINGREPOVIEWMODEL", "AnnualGDPRepository: findAnnualGDP -- prequery");
            results = macroEconomicsDao.findAnnualGDP(startYear, endYear);
            Log.println(Log.INFO, "TESTINGREPOVIEWMODEL", "AnnualGDPRepository: findAnnualGDP -- postquery");
//            handler.sendEmptyMessage(0);
            String graphType = "MEG2";
            Message msg = Message.obtain();
            msg.obj = graphType;
            msg.setTarget(handler);
            msg.sendToTarget();
        });
        executor.shutdown();
    }

    public void insertAnnualGDPs(MacroEconomicsEntity macroEconomicsEntity) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            Log.println(Log.INFO, "TESTINGREPOVIEWMODEL", "AnnualGDPRepository: insertAnnualGDP -- prequery");
            macroEconomicsDao.insertAnnualGDP(macroEconomicsEntity);
            Log.println(Log.INFO, "TESTINGREPOVIEWMODEL", "AnnualGDPRepository: insertAnnualGDP -- postquery");
//            handler.sendEmptyMessage(0);
        });
        executor.shutdown();
    }

//    public MutableLiveData<List<AnnualGDPEntity>> getSearchResults() {
//        Log.println(Log.INFO, "TESTINGREPOVIEWMODEL", "AnnualGDPRepository: getSearchResults");
//        return searchResults;
//    }

    public MutableLiveData<HashMap<String, List<MacroEconomicsEntity>>> getSearchResults(){
        return searchResults;
    }

}
