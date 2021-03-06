package com.example.hackathon.ui.agriculture;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.hackathon.R;
import com.example.hackathon.ui.macroeconomic.MacroEconomicFragment;
import com.example.hackathon.ui.macroeconomic.MacroEconomicGraphFragment;

import java.util.ArrayList;

public class AgricultureDataSearchFragment extends Fragment {
    ArrayList<String> selectedStrings = new ArrayList<String>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_argiculture_data_search, container, false);
        ArrayList<View> checkboxes = new ArrayList<>();
        view.findViewsWithText(checkboxes, "AGRICULTURE", View.FIND_VIEWS_WITH_CONTENT_DESCRIPTION);
        checkboxes.forEach((chk) -> {
            CheckBox checkBox = (CheckBox) chk;
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    String resourceId = view.getResources().getResourceName(compoundButton.getId());
                    String graphId = resourceId.substring(resourceId.lastIndexOf("/") + 1);
                    if (b) {
                        selectedStrings.add(graphId);
                    } else {
                        selectedStrings.remove(graphId);
                    }
                }
            });
        });

        Button searchButton = view.findViewById(R.id.buttonSearchAgriculture);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedStrings.size() == 0) {
                    Toast.makeText(view.getContext(), "Select at least one data source", Toast.LENGTH_SHORT).show();
                    return;
                }
                AgricultureFragment parentFragment = (AgricultureFragment) getParentFragment();
                (parentFragment.getAgricultureViewModel()).setCheckedGraphs(selectedStrings);
                parentFragment.replaceFragments(AgricultureGraphFragment.class);

            }
        });
        return view;
    }
}