<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.inventory.model.Product"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Products</title>

<style>
.container {
    display: flex;
    flex-wrap: wrap;
    gap: 20px;
    justify-content: center;
}

.card {
    background: white;
    padding: 15px;
    width: 200px;
    border-radius: 15px;
    text-align: center;
}
</style>
</head>

<body>

<jsp:include page="header.jsp"/>

<div class="container">

<%
List<Product> products = (List<Product>) request.getAttribute("productList");
for(Product p : products){
%>

<div class="card">
<h3><%= p.getName() %></h3>
<p>₹<%= p.getPrice() %></p>
<p>Qty: <%= p.getQuantity() %></p>
</div>

<%
}
%>

</div>

<jsp:include page="footer.jsp"/>

</body>
</html>