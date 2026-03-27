package com.inventory.service;

import com.inventory.model.Product;
import com.inventory.util.*;
import com.inventory.util.DBConnection;
import com.inventory.util.DataStore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class InventoryService {

//    public void addProduct(Product p) {
//        DataStore.products.add(p);
//    }
//
//    public List<Product> getAllProducts() {
//        return DataStore.products;
//    }

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
    
    public boolean deleteProduct(int id) {
        return dao.deleteProduct(id);
    }

    public boolean updateProduct(Product p) {
        return dao.updateProduct(p);
    }
    
    public boolean isDuplicateId(int id) {
        return DataStore.products.stream()
                .anyMatch(p -> p.getId() == id);
    }
    
    public void saveSale(int productId, String name, int qty, double total) {
	    try (Connection con = DBConnection.getConnection()) {

	        String sql = "INSERT INTO sales(product_id, product_name, quantity, total_price) VALUES(?,?,?,?)";
	        PreparedStatement ps = con.prepareStatement(sql);

	        ps.setInt(1, productId);
	        ps.setString(2, name);
	        ps.setInt(3, qty);
	        ps.setDouble(4, total);

	        ps.executeUpdate();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	public Product getProductById(int id) {

	    String sql = "SELECT * FROM products WHERE id=?";

	    try (Connection con = DBConnection.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setInt(1, id);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            return new Product(
	                rs.getInt("id"),
	                rs.getString("name"),
	                rs.getString("category"),
	                rs.getInt("quantity"),
	                rs.getDouble("price")
	            );
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return null;
	}
    private ProductDAO dao = new ProductDAO();


    public boolean addProduct(Product p) {
        return dao.addProduct(p);
    }

    public List<Product> getAllProducts() {
        return dao.getAllProducts();
    }
}