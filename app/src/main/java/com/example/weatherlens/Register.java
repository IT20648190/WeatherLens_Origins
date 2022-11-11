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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    EditText fname,lname,email,mobile,password,repassword;
    Button register;
    TextView loginhere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fname = findViewById(R.id.firstname);
        lname = findViewById(R.id.lastname);
        email = findViewById(R.id.email);
        mobile = findViewById(R.id.mobile);
        password = findViewById(R.id.password);
        repassword = findViewById(R.id.repassword);
        register = findViewById(R.id.register_btn);
        loginhere = findViewById(R.id.loginhere);

        loginhere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login = new Intent(Register.this,loginpage.class);
                startActivity(login);
                finish();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(fname.getText().toString())){
                   fname.setError("First name is compulsory");
                    return;
                }
                if(TextUtils.isEmpty(lname.getText().toString())){
                    lname.setError("Last name is compulsory");
                    return;
                }
                if(TextUtils.isEmpty(email.getText().toString())){
                    email.setError("Email is compulsory");
                    return;
                }
                if(TextUtils.isEmpty(mobile.getText().toString())){
                    mobile.setError("Mobile Number is compulsory");
                    return;
                }
                if(TextUtils.isEmpty(password.getText().toString())){
                    password.setError("Password is compulsory");
                    return;
                }
                if(TextUtils.isEmpty(repassword.getText().toString())){
                    repassword.setError("Confirm password is compulsory");
                    return;
                }

                registerUser();
            }
        });


    }


    private void registerUser(){

        String str_fname=fname.getText().toString();
        String str_lname=lname.getText().toString();
        String str_email=email.getText().toString();
        String str_mobile= mobile.getText().toString();
        String str_password=password.getText().toString();
        String str_type="user";


        User user  = new User(str_fname,str_lname,str_email,str_mobile,str_password,str_type);



        FirebaseDatabase.getInstance().getReference().child("Users").child(str_mobile).setValue(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        fname.setText("");
                        lname.setText("");
                        email.setText("");
                        mobile.setText("");
                        email.setText("");
                        password.setText("");
                        repassword.setText("");
                        Toast.makeText(Register.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(Register.this, "Registration Failed", Toast.LENGTH_SHORT).show();


                    }
                });



    }
}