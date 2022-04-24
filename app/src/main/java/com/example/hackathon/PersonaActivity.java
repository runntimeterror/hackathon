package com.example.hackathon;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hackathon.state.Persona;

public class PersonaActivity extends AppCompatActivity {
    private static final int WRITE_REQUEST_CODE = 100;

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case WRITE_REQUEST_CODE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    //Denied.
                }
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.INTERNET};
        requestPermissions(permissions, WRITE_REQUEST_CODE);

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
