package com.example.weatherlens.Models;

public class User {
    String firstname;
    String lastname;
    String email;
    String  mobile;
    String password;
    String type;

    public User() {

    }

    public User(String firstname, String lastname, String email, String mobile, String password,String type) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
        this.type = type;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;


    }
}
