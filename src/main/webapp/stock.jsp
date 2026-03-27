<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Stock</title>
</head>

<body>

<jsp:include page="header.jsp"/>

<h2 style="text-align:center;">Stock Management</h2>

<form action="inventory" method="post" style="text-align:center;">
    ID: <input type="number" name="id"/><br/>
    Quantity: <input type="number" name="quantity"/><br/>

    <button name="action" value="stockIn">Stock In</button>
    <button name="action" value="stockOut">Stock Out</button>
</form>

<jsp:include page="footer.jsp"/>

</body>
</html>