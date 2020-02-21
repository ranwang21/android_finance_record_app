package com.example.myfinancials.entities;

import java.util.Date;


public class Record {

    private double amount;
    private String description;
    private String date;
    private int id_category;
    private int id_user;

    public Record(double amount, String description, String date, int id_category, int id_user) {
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.id_category = id_category;
        this.id_user = id_user;
    }



    public Record() {
    }


    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId_category() {
        return id_category;
    }

    public void setId_category(int id_category) {
        this.id_category = id_category;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
}
