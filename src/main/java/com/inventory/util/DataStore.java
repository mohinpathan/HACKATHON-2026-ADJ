package com.inventory.util;

import java.util.ArrayList;
import java.util.List;

import com.inventory.model.Product;

public class DataStore {

    public static List<Product> products = new ArrayList<>();
    private static int idCounter = 1;

    public static int getNextId() {
        return idCounter++;
    }

    // STATIC BLOCK = runs automatically when class loads
    static {
        products.add(new Product(1, "Fast Charger", "Charger", 20, 499));
        products.add(new Product(2, "Type-C Cable", "Cable", 5, 199));
        products.add(new Product(3, "Wireless Earbuds", "Audio", 8, 1499));
        products.add(new Product(4, "Mobile Cover", "Cover", 15, 299));
        products.add(new Product(5, "Power Bank", "Battery", 3, 999));
        products.add(new Product(6, "Screen Guard", "Protection", 25, 149));
    }
}