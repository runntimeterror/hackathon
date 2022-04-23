package com.example.hackathon.ui.macroeconomic;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.hackathon.R;
import com.example.hackathon.databinding.FragmentMacroeconomicBinding;
import com.example.hackathon.repository.AnnualGDPEntity;
import com.example.hackathon.ui.TestingRepoViewModel;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class MacroEconomicFragment extends Fragment {

    private FragmentMacroeconomicBinding binding;

    private TestingRepoViewModel testingRepoViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MacroEconomicViewModel macroEconomicViewModel =
                new ViewModelProvider(this).get(MacroEconomicViewModel.class);

        binding = FragmentMacroeconomicBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.textHome;
//        macroEconomicViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

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

        int[][] hardcodedSeedData = {
                {2014, 37029, 597164, 5433000},
                {2015, 3702980, 597160, 543300},
                {2016, 3702098, 59716, 543300},
                {2017, 370298, 597016, 543000},
                {2018, 3702098, 590716, 53300},
                {2019, 370298, 597106, 54300},
                {2020, 370298, 597016, 53300},
        };

        for (int[] seed : hardcodedSeedData) {
            AnnualGDPEntity entity = new AnnualGDPEntity();
            entity.setYear(seed[0]);
            entity.setIndiaGDP(seed[1]);
            entity.setChinaGDP(seed[2]);
            entity.setUsaGDP(seed[0]);

            testingRepoViewModel.insertGDPs(entity);
        }

//        try {
//            FileReader file = new FileReader("database/annualgdptable.csv");
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
//                entity.setIndiaGDP(Integer.valueOf(str[1]));
//                entity.setChinaGDP(Integer.valueOf(str[2]));
//                entity.setUsaGDP(Integer.valueOf(str[3]));
//                testingRepoViewModel.insertGDPs(entity);
//            }
////            db.setTransactionSuccessful();
////            db.endTransaction();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Fragment childFragment = new MacroEconomicDataSearchFragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.macroeconomic_child_fragment_container, childFragment).commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
