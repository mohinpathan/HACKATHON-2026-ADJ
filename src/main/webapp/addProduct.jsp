<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Product</title>

<style>
body {
    background: linear-gradient(120deg, #1e3c72, #2a5298);
    font-family: Arial;
}

.card {
    width: 350px;
    margin: 80px auto;
    padding: 20px;
    background: white;
    border-radius: 15px;
    box-shadow: 0 5px 20px rgba(0,0,0,0.3);
    animation: fadeIn 0.6s;
}

input, select {
    width: 100%;
    padding: 10px;
    margin: 8px 0;
    border-radius: 8px;
    border: 1px solid #ccc;
}

button {
    width: 100%;
    padding: 10px;
    background: #2a5298;
    color: white;
    border: none;
    border-radius: 8px;
}

@keyframes fadeIn {
    from {opacity:0;}
    to {opacity:1;}
}
</style>
</head>

<body>

<jsp:include page="header.jsp"/>

<div class="card">
<h2>Add Product</h2>

<form action="inventory" method="post">
    <input type="hidden" name="action" value="add"/>

    <input type="text" name="name" placeholder="Product Name" required/>

    <select name="category">
        <option>Charger</option>
        <option>Cable</option>
        <option>Audio</option>
        <option>Cover</option>
        <option>Mobile Phone</option>
    </select>

    <input type="number" name="quantity" placeholder="Quantity" required/>
    <input type="number" name="price" placeholder="Price" required/>

    <button type="submit">Add Product</button>
</form>
</div>

<jsp:include page="footer.jsp"/>

</body>
</html>