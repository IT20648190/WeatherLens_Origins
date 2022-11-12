package com.example.weatherlens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class HazardDetails extends AppCompatActivity {

    RecyclerView recview;
    Hazadapter hazadapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hazard_details);

        recview = (RecyclerView) findViewById(R.id.recview);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1);
        recview.setLayoutManager(gridLayoutManager);

        FirebaseRecyclerOptions<modelhazard> options =
                new FirebaseRecyclerOptions.Builder<modelhazard>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Hazard"), modelhazard.class)
                        .build();

        hazadapter = new Hazadapter(options);
        recview.setAdapter(hazadapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        hazadapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        hazadapter.stopListening();
    }
}