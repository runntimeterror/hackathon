package com.example.hackathon.ui.macroeconomic;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import com.example.hackathon.R;
import com.example.hackathon.databinding.FragmentMacroeconomicBinding;
import com.example.hackathon.repository.AnnualGDPEntity;
import com.example.hackathon.repository.AnnualGDPRepository;

import java.util.HashMap;
import java.util.List;

public class MacroEconomicFragment extends Fragment {
    private FragmentMacroeconomicBinding binding;
    private MacroEconomicViewModel macroEconomicViewModel;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        macroEconomicViewModel =
                new ViewModelProvider(this).get(MacroEconomicViewModel.class);

        binding = FragmentMacroeconomicBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    public MacroEconomicViewModel getMacroEconomicViewModel() {
        return macroEconomicViewModel;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Fragment childFragment = new MacroEconomicDataSearchFragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.macroeconomic_child_fragment_container, childFragment).commit();
    }

    public void replaceFragments(Class fragmentClass) {
        Fragment fragment = null;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.macroeconomic_child_fragment_container, fragment).commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
