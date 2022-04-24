package com.example.hackathon.ui.macroeconomic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.hackathon.R;
import com.example.hackathon.databinding.FragmentMacroeconomicBinding;
import com.example.hackathon.ui.TestingRepoViewModel;

public class MacroEconomicFragment extends Fragment {

    private FragmentMacroeconomicBinding binding;

    private TestingRepoViewModel testingRepoViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MacroEconomicViewModel macroEconomicViewModel =
                new ViewModelProvider(this).get(MacroEconomicViewModel.class);

        binding = FragmentMacroeconomicBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

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
