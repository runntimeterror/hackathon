package com.example.hackathon.state;

public class Persona {
    private static Persona persona_instance = null;
    public static final String GOVT_EMPLOYEE = "GOVT_EMPLOYEE";
    public static final String DATA_SCIENTIST = "DATA_SCIENTIST";
    public String userPersona;
    private Persona()
    {
    }


    public static Persona getInstance()
    {
        if (persona_instance == null)
            persona_instance = new Persona();
        return persona_instance;
    }

    public void setUserPersona(String userPersona) {
        this.userPersona = userPersona;
    }

    public String getUserPersona() {
        return userPersona;
    }
}
