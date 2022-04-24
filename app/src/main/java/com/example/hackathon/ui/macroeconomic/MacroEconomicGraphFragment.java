package com.example.hackathon.ui.macroeconomic;

import android.content.res.AssetFileDescriptor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.hackathon.R;
import com.example.hackathon.repository.AnnualGDPEntity;
import com.example.hackathon.ui.TestingRepoViewModel;

import java.util.List;

public class MacroEconomicGraphFragment extends Fragment {
    private TestingRepoViewModel testingRepoViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        testingRepoViewModel = new ViewModelProvider(this).get(TestingRepoViewModel.class);
        testingRepoViewModel.getSearchResults().observe(
                getViewLifecycleOwner(),
                new Observer<List<AnnualGDPEntity>>() {
                    @Override
                    public void onChanged(@Nullable final List<AnnualGDPEntity> gdps) {
                        Log.println(Log.INFO, "TESTINGREPOVIEWMODEL",
                                "MacroEconomicFragment:searchResults.observer.onChanged Length:" + gdps.size());
                    }
                }
        );

//        binding.testQueryButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.println(Log.INFO, "TESTINGREPOVIEWMODEL", "MacroEconomicFragment: onClick -> viewmodel.findAnnualGDP");
//                testingRepoViewModel.findAnnualGDPs(2015, 2019);
//            }
//        });

        Long[][] hardcodedSeedData = {
                {37029L, 597164L, 5433000L},
                {3702980L, 597160L, 543300L},
                {3702098L, 59716L, 543300L},
                {370298L, 597016L, 543000L},
                {3702098L, 590716L, 53300L},
                {370298L, 597106L, 54300L},
                {370298L, 597016L, 53300L},
        };

        int year = 2014;
        for (Long[] seed : hardcodedSeedData) {
            AnnualGDPEntity entity = new AnnualGDPEntity();
            entity.setYear(year++);
            entity.setIndiaGDP(seed[0]);
            entity.setChinaGDP(seed[1]);
            entity.setUsaGDP(seed[2]);

            testingRepoViewModel.insertGDPs(entity);
        }

        testingRepoViewModel.findAnnualGDPs(2014, 2020);

//        try {
////            AssetFileDescriptor descriptor = getContext().getAssets().open("annualgdptable.csv");
////            Log.println(Log.INFO, "TESTINGREPOVIEWMODEL", descriptor.getFileDescriptor().toString());
////            InputStream file = getContext().getAssets().open("images/names.txt");
//
//            FileReader file = new FileReader("annualgdptable.csv");
////            BufferedReader buffer = new BufferedReader(new InputStreamReader(file));
//            BufferedReader buffer = new BufferedReader(file);
//            String line = "";
////            String tableName ="annualgdps";
////            String columns = "year, india, china, usa";
////            String str1 = "INSERT INTO " + tableName + " (" + columns + ") values(";
////            String str2 = ");";
//
////            db.beginTransaction();
//            while ((line = buffer.readLine()) != null) {
////                StringBuilder sb = new StringBuilder(str1);
//                String[] str = line.split(",");
//                AnnualGDPEntity entity = new AnnualGDPEntity();
//                entity.setYear(Integer.valueOf(str[0]));
//                entity.setIndiaGDP(Long.valueOf(str[1]));
//                entity.setChinaGDP(Long.valueOf(str[2]));
//                entity.setUsaGDP(Long.valueOf(str[3]));
//                testingRepoViewModel.insertGDPs(entity);
//            }
////            db.setTransactionSuccessful();
////            db.endTransaction();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        testingRepoViewModel.findAnnualGDPs(1960, 2020);

        return inflater.inflate(R.layout.fragment_macroeconomic_graph, container, false);
    }
}
