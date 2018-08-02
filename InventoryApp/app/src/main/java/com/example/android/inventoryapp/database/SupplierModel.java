package com.example.android.inventoryapp.database;

public class SupplierModel {

    //supplier properties
    int id, categoryId;
    String name, email, phone, web;

    //constructor
    public SupplierModel(){
    }

    public SupplierModel(int id, int categoryId, String name, String email, String phone, String web) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.web = web;
    }


    //getters
    public int getId() {
        return id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getWeb() {
        return web;
    }

    //setters
    public void setId(int id) {
        this.id = id;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setWeb(String web) {
        this.web = web;
    }
}

