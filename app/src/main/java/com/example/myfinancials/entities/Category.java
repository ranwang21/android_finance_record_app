package com.example.myfinancials.entities;

public class Category {

    private int id_category;
    private String nom_category;
    public Category() {
    }
    public Category(String nom_category) {
        this.nom_category = nom_category;
    }
    public int getId_category() {
        return id_category;
    }
    public void setId_category(int id_category) {
        this.id_category = id_category;
    }
    public String getNom_category() {
        return nom_category;
    }
    public void setNom_category(String nom_category) {
        this.nom_category = nom_category;
    }
}
