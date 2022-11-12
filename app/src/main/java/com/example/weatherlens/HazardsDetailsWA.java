package com.example.weatherlens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class HazardsDetailsWA extends AppCompatActivity {

    RecyclerView recview;
    WAHazadapter waHazadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hazards_details_wa);

        recview = (RecyclerView) findViewById(R.id.recviewwa);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1);
        recview.setLayoutManager(gridLayoutManager);

        FirebaseRecyclerOptions<modelhazard> options =
                new FirebaseRecyclerOptions.Builder<modelhazard>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Hazard"), modelhazard.class)
                        .build();

        waHazadapter = new WAHazadapter(options);
        recview.setAdapter(waHazadapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        waHazadapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        waHazadapter.stopListening();
    }
}