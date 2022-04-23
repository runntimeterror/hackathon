package com.example.hackathon.state;

public class Country {
    private static Country country_instance = null;
    public static final String USA = "USA";
    public static final String INDIA = "INDIA";
    public static final String CHINA = "CHINA";
    private String selectedCountry;

    private Country() {
    }


    public static Country getInstance() {
        if (country_instance == null)
            country_instance = new Country();
        return country_instance;
    }

    public void setCountrySelection(String selectedCountry) {
        this.selectedCountry = selectedCountry;
    }

    public String getSelectedCountry() {
        return selectedCountry;
    }
}
