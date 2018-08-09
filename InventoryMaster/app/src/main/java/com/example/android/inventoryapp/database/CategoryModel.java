package com.example.android.inventoryapp.database;

public class CategoryModel {

    //category properties
    int id;
    String name;

    //constructor
    public CategoryModel(){
    }

    //this is used to create a new category
    public CategoryModel(String name) {
        this.name = name;
    }

    public CategoryModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    //getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    //setters

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
