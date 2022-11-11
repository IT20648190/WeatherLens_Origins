package com.example.weatherlens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weatherlens.Models.User;
import com.example.weatherlens.Session.SessionManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class loginpage extends AppCompatActivity {
    EditText mobile,password;
    Button login;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference Userdb = database.getReference("Users");
    SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);

        mobile = findViewById(R.id.loginmobile);
        password = findViewById(R.id.loginpassword);
        login = findViewById(R.id.loginbtn);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(mobile.getText().toString())){
                    mobile.setError("Mobile Number is compulsory");
                    return;
                }
                if(TextUtils.isEmpty(password.getText().toString())){
                    password.setError("Password is compulsory");
                    return;
                }

                loginUser();

            }
        });



    }

    private void loginUser(){

        Userdb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //check if model already available
                if (snapshot.child(mobile.getText().toString()).exists()) {
                    //get user information
                    User user = snapshot.child(mobile.getText().toString()).getValue(User.class);
                        if (user.getPassword().equals(password.getText().toString())) {


                            Toast.makeText(loginpage.this, "Login Success", Toast.LENGTH_SHORT).show();

                            session = new SessionManager(getApplicationContext());
                            session.createLoginSession(user.getFirstname(), user.getLastname(), user.getMobile(), user.getEmail(),"user");

                            Intent dashboard = new Intent(loginpage.this, UserDashboard.class);
                            startActivity(dashboard);
                            finish();

                        } else {
                            System.out.println("Failed");
                            Toast.makeText(loginpage.this, "Login failed", Toast.LENGTH_SHORT).show();
                        }

                }
                else {
                    Toast.makeText(loginpage.this, "Please Register", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(loginpage.this, Register.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }






}