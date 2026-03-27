package com.inventory.service;

import com.inventory.model.Product;
import com.inventory.util.DataStore;

import java.util.List;

public class InventoryService {

    public void addProduct(Product p) {
        DataStore.products.add(p);
    }

    public List<Product> getAllProducts() {
        return DataStore.products;
    }

    public Product findProduct(int id) {
        return DataStore.products.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void stockIn(int id, int qty) {
        Product p = findProduct(id);
        if (p != null) {
            p.setQuantity(p.getQuantity() + qty);
        }
    }

    public void stockOut(int id, int qty) {
        Product p = findProduct(id);
        if (p != null && p.getQuantity() >= qty) {
            p.setQuantity(p.getQuantity() - qty);
        }
    }
}