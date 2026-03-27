package com.inventory.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import com.inventory.util.*;
import com.inventory.model.Product;
import com.inventory.service.InventoryService;
import com.inventory.util.DataStore; 

public class InventoryControllerServlet extends HttpServlet {

    private InventoryService service = new InventoryService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Product> products = service.getAllProducts(); // now from DB

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

        	    String name = request.getParameter("name");
        	    String category = request.getParameter("category");
        	    int qty = Integer.parseInt(request.getParameter("quantity"));
        	    double price = Double.parseDouble(request.getParameter("price"));

        	    Product p = new Product(0, name, category, qty, price);

        	    boolean success = service.addProduct(p);

        	    if (success) {
        	        response.sendRedirect("addProduct.jsp?msg=success");
        	    } else {
        	        response.sendRedirect("addProduct.jsp?msg=error");
        	    }

        	    return;
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
            else if ("update".equals(action)) {

                int id = Integer.parseInt(request.getParameter("id"));
                String name = request.getParameter("name");
                String category = request.getParameter("category");
                int qty = Integer.parseInt(request.getParameter("quantity"));
                double price = Double.parseDouble(request.getParameter("price"));

                Product p = new Product(id, name, category, qty, price);

                boolean success = service.updateProduct(p);

                if (success) {
                    response.sendRedirect("home?msg=updated");
                } else {
                    response.sendRedirect("home?msg=error");
                }

                return;
            }
            else if ("delete".equals(action)) {

                int id = Integer.parseInt(request.getParameter("id"));

                boolean success = service.deleteProduct(id);

                if (success) {
                    response.sendRedirect("home?msg=deleted");
                } else {
                    response.sendRedirect("home?msg=error");
                }

                return;
            }
            else if ("getProduct".equals(action)) {

                int id = Integer.parseInt(request.getParameter("id"));

                Product p = service.getProductById(id);

                response.setContentType("application/json");

                if (p != null) {
                    response.getWriter().write(
                        "{\"name\":\"" + p.getName() + "\", \"qty\":" + p.getQuantity() + "}"
                    );
                } else {
                    response.getWriter().write("{\"error\":\"not found\"}");
                }

                return;
            }
            else if ("sale".equals(action)) {

                int productId = Integer.parseInt(request.getParameter("productId"));
                int qty = Integer.parseInt(request.getParameter("quantity"));

                Product p = service.findProduct(productId);

                if (p != null && p.getQuantity() >= qty) {

                    double total = qty * p.getPrice();

                    service.stockOut(productId, qty); // reduce stock
                    service.saveSale(productId, p.getName(), qty, total);

                    response.sendRedirect("sales.jsp?msg=success");

                } else {
                    response.sendRedirect("sales.jsp?msg=error");
                }
            }

            response.sendRedirect("home");

        } catch (Exception e) {
            response.sendRedirect("error.jsp");
        }
    }
}