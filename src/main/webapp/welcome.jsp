<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.inventory.model.Product"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>
</head>

<body>

<jsp:include page="header.jsp"/>

<h1 style="text-align:center;">Inventory Dashboard</h1>

<table border="1" align="center">

<tr>
<th>ID</th>
<th>Name</th>
<th>Category</th>
<th>Quantity</th>
<th>Price</th>
</tr>

<%
List<Product> products = (List<Product>) request.getAttribute("productList");


if(products != null){
    for(Product p : products){
    	String cls = (p.getQuantity() <= p.getLowStockThreshold()) ? "low" : "";
%>
<tr>

<td><%= p.getId() %></td>
<td><%= p.getName() %></td>
<td><%= p.getCategory() %></td>
<td><%= p.getQuantity() %></td>
<td>₹<%= p.getPrice() %></td>
</tr>
<%
    }
}
%>

</table>


<jsp:include page="footer.jsp"/>

</body>
</html>