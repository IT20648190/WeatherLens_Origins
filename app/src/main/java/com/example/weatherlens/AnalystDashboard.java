package com.example.weatherlens;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class AnalystDashboard extends AppCompatActivity {

    ImageButton viewall, addhazard, addweather ;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analyst_dashboard);

        viewall = findViewById(R.id.view_hazards);
        viewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnalystDashboard.this,HazardsDetailsWA.class);
                startActivity(intent);
            }
        });

        addhazard = findViewById(R.id.btn_addhazard);
        addhazard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnalystDashboard.this,AddHazard.class);
                startActivity(intent);
            }
        });

        addweather = findViewById(R.id.btn_addweather);
        addweather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnalystDashboard.this,AddWeather.class);
                startActivity(intent);
            }
        });

    }
}