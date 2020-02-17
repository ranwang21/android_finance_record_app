package com.example.myfinancials.entities;

import java.util.Date;


public class Record {

    private int id_record;
    private double amount;
    private String description;
    private User user;
    private Category category;
    private Date date;
    public Record() {
    }
    public Record(int id_record, double amount, String description, User user, Category category, Date date) {
        this.id_record = id_record;
        this.amount = amount;
        this.description = description;
        this.user = user;
        this.category = category;
        this.date = date;
    }
    public int getId_record() {
        return id_record;
    }
    public void setId_record(int id_record) {
        this.id_record = id_record;
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
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
}
