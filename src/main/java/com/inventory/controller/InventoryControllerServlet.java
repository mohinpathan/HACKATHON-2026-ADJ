package com.inventory.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import com.inventory.model.Product;
import com.inventory.service.InventoryService;

public class InventoryControllerServlet extends HttpServlet {

    private InventoryService service = new InventoryService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Product> products = service.getAllProducts();
        request.setAttribute("productList", products);

        RequestDispatcher rd = request.getRequestDispatcher("welcome.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        try {
            if ("add".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                String name = request.getParameter("name");
                String category = request.getParameter("category");
                int qty = Integer.parseInt(request.getParameter("quantity"));
                double price = Double.parseDouble(request.getParameter("price"));

                Product p = new Product(id, name, category, qty, price);
                service.addProduct(p);
            }

            else if ("stockIn".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                int qty = Integer.parseInt(request.getParameter("quantity"));
                service.stockIn(id, qty);
            }

            else if ("stockOut".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                int qty = Integer.parseInt(request.getParameter("quantity"));
                service.stockOut(id, qty);
            }

            response.sendRedirect("home");

        } catch (Exception e) {
            response.sendRedirect("error.jsp");
        }
    }
}