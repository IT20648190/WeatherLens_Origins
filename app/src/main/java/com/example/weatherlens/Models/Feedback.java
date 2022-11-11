package com.example.weatherlens.Models;

public class Feedback {
    Number feedback;
    String email;
    String mobile;

    public Feedback() {

    }

    public Feedback(Number feedback, String email, String mobile) {
        this.feedback = feedback;
        this.email = email;
        this.mobile = mobile;
    }

    public Number getFeedback() {
        return feedback;
    }

    public void setFeedback(Number feedback) {
        this.feedback = feedback;
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
}
