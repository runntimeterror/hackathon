package com.example.hackathon;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.hackathon.databinding.ActivityMainBinding;
import com.example.hackathon.state.Country;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    ImageView countrySelector, iconUsa, iconIndia, iconChina;
    boolean isFABOpen = false;
    Country countryInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        countryInstance = Country.getInstance();
        countryInstance.setCountrySelection(Country.USA);
        //country selector
        countrySelector = (ImageView) findViewById(R.id.countrySelector);
        iconUsa = (ImageView) findViewById(R.id.selectUsa);
        iconIndia = (ImageView) findViewById(R.id.selectIndia);
        iconChina = (ImageView) findViewById(R.id.selectChina);

        countrySelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFABOpen) {
                    showFABMenu();
                } else {
                    closeFABMenu();
                }
            }
        });


        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_macroeconomic, R.id.navigation_agriculture, R.id.navigation_debt)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    private void showFABMenu() {
        isFABOpen = true;
        iconUsa.animate().translationX(-getResources().getDimension(R.dimen.standard_55));
        iconIndia.animate().translationX(-getResources().getDimension(R.dimen.standard_105));
        iconChina.animate().translationX(-getResources().getDimension(R.dimen.standard_155));
        countrySelector.setImageResource(R.drawable.icon_cancel);
    }

    private void closeFABMenu() {
        countryInstance = Country.getInstance();
        switch (countryInstance.getSelectedCountry()) {
            case Country.USA:
                countrySelector.setImageResource(R.drawable.usa_flag);
                break;
            case Country.INDIA:
                countrySelector.setImageResource(R.drawable.india_flag);
                break;
            case Country.CHINA:
                countrySelector.setImageResource(R.drawable.china_flag);
                break;
        }
        isFABOpen = false;
        iconUsa.animate().translationX(0);
        iconIndia.animate().translationX(0);
        iconChina.animate().translationX(0);
    }

    public void selectUsa(View v) {
        countryInstance = Country.getInstance();
        countryInstance.setCountrySelection(Country.USA);
        closeFABMenu();
    }

    public void selectIndia(View v) {
        countryInstance = Country.getInstance();
        countryInstance.setCountrySelection(Country.INDIA);
        closeFABMenu();
    }

    public void selectChina(View v) {
        countryInstance = Country.getInstance();
        countryInstance.setCountrySelection(Country.CHINA);
        closeFABMenu();
    }
}