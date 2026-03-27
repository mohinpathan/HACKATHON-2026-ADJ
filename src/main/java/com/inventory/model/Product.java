package com.inventory.model;

public class Product {
    private int id;
    private String name;
    private String category;
    private int quantity;
    private double price;
    private int lowStockThreshold = 5;

    public Product(int id, String name, String category, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }
    public int getLowStockThreshold() { return lowStockThreshold; }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}