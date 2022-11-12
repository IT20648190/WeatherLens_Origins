package com.example.weatherlens;

public class modelhazard {
    String title;
    String desc;
    String type;
    String date;

    modelhazard(){}

    public modelhazard(String title, String desc, String type, String date) {
        this.title = title;
        this.desc = desc;
        this.type = type;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
