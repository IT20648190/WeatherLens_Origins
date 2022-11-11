package com.example.weatherlens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weatherlens.Models.Feedback;
import com.example.weatherlens.Models.User;
import com.example.weatherlens.Session.SessionManager;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

public class Feedbackpage extends AppCompatActivity {
    SessionManager session;
    TextView feedback;
    Button feedback_btn;
    String email,mobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedbackpage);

        feedback = findViewById(R.id.insertfeedback);
        feedback_btn = findViewById(R.id.feedbackbtn);
        session = new SessionManager(getApplicationContext());
        email  = session.getUserDetails().get("email");
        mobile  = session.getUserDetails().get("mobile");

        feedback_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(feedback.getText().toString())){
                    feedback.setError("Feedback Value is compulsory");
                    return;
                }

                addFeedback();
            }
        });




    }

    private void addFeedback(){

        String str_feedback=feedback.getText().toString();
//        Number feedback = Integer.parseInt(str_feedback);

        Feedback feedbackmodel = new Feedback(Integer.parseInt(str_feedback),email,mobile);

        FirebaseDatabase.getInstance().getReference().child("Feedback").child(mobile).setValue(feedbackmodel)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        feedback.setText("");

                        Toast.makeText(Feedbackpage.this, "Feedback Sent", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(Feedbackpage.this, "Feedback Sending Failed", Toast.LENGTH_SHORT).show();


                    }
                });



    }
}