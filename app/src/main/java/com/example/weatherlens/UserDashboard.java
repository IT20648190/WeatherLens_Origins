package com.example.weatherlens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.weatherlens.Session.SessionManager;

public class UserDashboard extends AppCompatActivity {

    SessionManager session;
    TextView hellouser;
    ImageButton logout,feedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);

        session = new SessionManager(getApplicationContext());

        logout = findViewById(R.id.logoutbtn);
        feedback = findViewById(R.id.addfeedbackbtn);

        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserDashboard.this,Feedbackpage.class);
                startActivity(intent);
                finish();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                session.logoutUser();
            }
        });



    }
}