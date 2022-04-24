package com.example.hackathon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hackathon.state.Persona;

public class PersonaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persona);
        Persona appPersona = Persona.getInstance();
        ImageButton buttonGovPersona = (ImageButton) findViewById(R.id.buttonGovEmployeePersona);
        ImageButton buttonDataPersona = (ImageButton) findViewById(R.id.buttonDataScientistPersona);
        buttonGovPersona.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appPersona.setUserPersona(appPersona.GOVT_EMPLOYEE);
                switchToMainActivity(v);
            }
        });
        buttonDataPersona.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appPersona.setUserPersona(appPersona.DATA_SCIENTIST);
                switchToMainActivity(v);
            }
        });
    }


    public void switchToMainActivity(View view){
        Intent intent = new Intent(PersonaActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
