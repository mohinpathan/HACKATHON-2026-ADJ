<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Product</title>
</head>

<body>

<jsp:include page="header.jsp"/>

<h2 style="text-align:center;">Add Product</h2>

<form action="inventory" method="post" style="text-align:center;">
    <input type="hidden" name="action" value="add"/>

    ID: <input type="number" name="id"/><br/>
    Name: <input type="text" name="name"/><br/>
    Category: <input type="text" name="category"/><br/>
    Quantity: <input type="number" name="quantity"/><br/>
    Price: <input type="number" name="price"/><br/>

    <button type="submit">Add</button>
</form>

<jsp:include page="footer.jsp"/>

</body>
</html>