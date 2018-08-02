package com.example.android.inventoryapp.database;

public class ProductModel {

    //product properties
    int id, price, quantity, imageId, categoryId, supplierId;
    String name, description;

    //constructor - needed so the product object can be accessed
    public ProductModel(){
    }

    public ProductModel(int _id, int price, int quantity, int imageId, int categoryId, int supplierId, String name, String description) {
        this.id = _id;
        this.price = price;
        this.quantity = quantity;
        this.imageId = imageId;
        this.categoryId = categoryId;
        this.supplierId = supplierId;
        this.name = name;
        this.description = description;
    }

    //getters so the view model can get the product details
    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getImageId() {
        return imageId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    //setters so new products can be added
    public void setId(int id) {
        this.id = id;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
