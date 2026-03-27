<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.inventory.model.Product"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sales Page</title>

<style>
body {
    background: linear-gradient(120deg, #1e3c72, #2a5298);
    font-family: Arial;
}

.card {
    width: 400px;
    margin: 80px auto;
    padding: 20px;
    background: white;
    border-radius: 15px;
}

input, select {
    width: 100%;
    padding: 10px;
    margin: 10px 0;
}

button {
    width: 100%;
    padding: 10px;
    background: green;
    color: white;
    border: none;
}
</style>
</head>

<body>

<jsp:include page="header.jsp"/>

<div class="card">
<h2>💰 Sell Product</h2>

<form action="inventory" method="post">
    <input type="hidden" name="action" value="sale"/>

    Product ID:
    <input type="number" name="productId" required/>

    Quantity Sold:
    <input type="number" name="quantity" required/>

    <button type="submit">Sell</button>
</form>
</div>

<jsp:include page="footer.jsp"/>

</body>
</html>