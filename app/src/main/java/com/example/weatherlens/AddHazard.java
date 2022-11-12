package com.example.weatherlens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.weatherlens.Models.Hazard;
import com.example.weatherlens.Models.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddHazard extends AppCompatActivity {

    EditText haztitle, hazdesc, haztype, hazdate;
    Button submit;
    DatabaseReference dbref;
    Hazard hzd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hazard);

        haztitle = findViewById(R.id.et_title);
        hazdesc = findViewById(R.id.et_desc);
        haztype = findViewById(R.id.et_type);
        hazdate = findViewById(R.id.et_date);
        submit = findViewById(R.id.haz_submit);

        hzd = new Hazard();
    }


    public void clearControls() {
        haztitle.setText("");
        hazdesc.setText("");
        haztype.setText("");
        hazdate.setText("");
    }

    public void Add(View view) {
        dbref = FirebaseDatabase.getInstance().getReference().child("Hazard");

        if (TextUtils.isEmpty(haztitle.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Please enter title", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(hazdesc.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Please enter the description", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(haztype.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Please enter the type", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(hazdate.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Please enter the date", Toast.LENGTH_SHORT).show();
        }
        else {
            hzd.setTitle(haztitle.getText().toString().trim());
            hzd.setDesc(hazdesc.getText().toString().trim());
            hzd.setType(haztype.getText().toString().trim());
            hzd.setDate(hazdate.getText().toString().trim());

            dbref.push().setValue(hzd);

            Toast.makeText(getApplicationContext(), "Hazard added successfully", Toast.LENGTH_SHORT).show();
            clearControls();
        }
    }
}