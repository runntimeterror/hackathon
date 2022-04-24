package com.example.hackathon.ui.debt;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.hackathon.R;
import com.example.hackathon.databinding.FragmentDebtBinding;
import com.example.hackathon.databinding.FragmentMacroeconomicBinding;
import com.example.hackathon.ui.macroeconomic.MacroEconomicViewModel;

public class DebtFragment extends Fragment {

    private FragmentDebtBinding binding;

    private DebtViewModel debtViewModel;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        debtViewModel =
                new ViewModelProvider(this).get(DebtViewModel.class);

        binding = FragmentDebtBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }


    public DebtViewModel getDebtViewModel() {
        return debtViewModel;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Fragment childFragment = new DebtDataSearchFragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.debt_child_fragment_container, childFragment).commit();
    }

    public void replaceFragments(Class fragmentClass) {
        Fragment fragment = null;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.debt_child_fragment_container, fragment).commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
