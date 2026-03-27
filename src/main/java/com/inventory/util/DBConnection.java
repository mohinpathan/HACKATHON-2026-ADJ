package com.inventory.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/inventory_db";
    private static final String USER = "root";
    private static final String PASS = "";  // ⚠️ XAMPP default is empty

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		String name=request.getParameter("name");
		String category=request.getParameter("category");
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		double price = Double.parseDouble(request.getParameter("price"));
		
		try
		{
			//load driver
			 Class.forName("com.mysql.cj.jdbc.Driver");
			//connection establish
			 Connection con = DriverManager.getConnection(
				        "jdbc:mysql://localhost:3306/inventory_db", "root", "");
			 PreparedStatement ps = con.prepareStatement(
				        "INSERT INTO products(name, category, quantity, price) VALUES(?,?,?,?)");
			
			 	ps.setString(1, name);
			    ps.setString(2, category);
			    ps.setInt(3, quantity);
			    ps.setDouble(4, price);

			    int count = ps.executeUpdate();

			    if (count == 1) {
			        pw.println("<h2>✅ Data inserted successfully</h2>");
			    } else {
			        pw.println("<h2>❌ Data not inserted</h2>");
			    }
			
			
		}
		catch(SQLException e)
		{
			pw.println("exception occured"+e);
			
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		
	}

}
