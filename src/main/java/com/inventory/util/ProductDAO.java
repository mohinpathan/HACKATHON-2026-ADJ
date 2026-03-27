package com.inventory.util;
import java.io.PrintWriter;
import com.inventory.model.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import java.sql.ResultSet;

public class ProductDAO {
	
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
	
	public boolean addProduct(Product p) {

	    String sql = "INSERT INTO products(name, category, quantity, price) VALUES(?,?,?,?)";

	    try (Connection con = DBConnection.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setString(1, p.getName());
	        ps.setString(2, p.getCategory());
	        ps.setInt(3, p.getQuantity());
	        ps.setDouble(4, p.getPrice());

	        int rows = ps.executeUpdate();
	        return rows > 0;

	    } catch (Exception e) {
	        System.out.println("Error adding product: " + e.getMessage());
	        return false;
	    }
	}
	
	public List<Product> getAllProducts() {

	    List<Product> list = new ArrayList<>();

	    String sql = "SELECT * FROM products";

	    try (Connection con = DBConnection.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {

	        while (rs.next()) {
	            Product p = new Product(
	                rs.getInt("id"),
	                rs.getString("name"),
	                rs.getString("category"),
	                rs.getInt("quantity"),
	                rs.getDouble("price")
	            );
	            list.add(p);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return list;
	}
	
	public boolean updateProduct(Product p) {

	    String sql = "UPDATE products SET name=?, category=?, quantity=?, price=? WHERE id=?";

	    try (Connection con = DBConnection.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setString(1, p.getName());
	        ps.setString(2, p.getCategory());
	        ps.setInt(3, p.getQuantity());
	        ps.setDouble(4, p.getPrice());
	        ps.setInt(5, p.getId());

	        int rows = ps.executeUpdate();
	        return rows > 0;

	    } catch (Exception e) {
	        System.out.println("Error updating product: " + e.getMessage());
	        return false;
	    }
	}
	
	public boolean deleteProduct(int id) {

	    String sql = "DELETE FROM products WHERE id=?";

	    try (Connection con = DBConnection.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setInt(1, id);

	        int rows = ps.executeUpdate();
	        return rows > 0;

	    } catch (Exception e) {
	        System.out.println("Error deleting product: " + e.getMessage());
	        return false;
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
	
	public boolean stockIn(int id, int qty) {

	    String sql = "UPDATE products SET quantity = quantity + ? WHERE id=?";

	    try (Connection con = DBConnection.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setInt(1, qty);
	        ps.setInt(2, id);

	        return ps.executeUpdate() > 0;

	    } catch (Exception e) {
	        return false;
	    }
	}
	
	public boolean stockOut(int id, int qty) {

	    String sql = "UPDATE products SET quantity = quantity - ? WHERE id=? AND quantity >= ?";

	    try (Connection con = DBConnection.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setInt(1, qty);
	        ps.setInt(2, id);
	        ps.setInt(3, qty);

	        return ps.executeUpdate() > 0;

	    } catch (Exception e) {
	        return false;
	    }
	}
}
