<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.inventory.model.Product, com.inventory.util.DataStore"%>

<%
int id = Integer.parseInt(request.getParameter("id"));
Product p = DataStore.products.stream()
    .filter(x -> x.getId() == id)
    .findFirst().orElse(null);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Product</title>

<style>
body {
    margin: 0;
    font-family: 'Segoe UI', sans-serif;
    background: linear-gradient(120deg, #1e3c72, #2a5298);
}

/* CARD */
.card {
    width: 400px;
    margin: 80px auto;
    padding: 25px;
    background: white;
    border-radius: 15px;
    box-shadow: 0px 5px 25px rgba(0,0,0,0.3);
    animation: fadeIn 0.5s ease-in;
}

h2 {
    text-align: center;
    margin-bottom: 20px;
}

/* INPUTS */
input {
    width: 100%;
    padding: 10px;
    margin: 10px 0;
    border-radius: 8px;
    border: 1px solid #ccc;
    transition: 0.3s;
}

input:focus {
    border-color: #2a5298;
    outline: none;
}

/* BUTTON */
button {
    width: 100%;
    padding: 12px;
    background: #2a5298;
    color: white;
    border: none;
    border-radius: 8px;
    cursor: pointer;
    transition: 0.3s;
}

button:hover {
    background: #1e3c72;
}

/* ANIMATION */
@keyframes fadeIn {
    from { opacity: 0; transform: translateY(20px);}
    to { opacity: 1; transform: translateY(0);}
}
</style>
</head>

<body>

<jsp:include page="header.jsp"/>

<div class="card">

    <h2>✏ Edit Product</h2>

    <form action="inventory" method="post">
        <input type="hidden" name="action" value="update"/>
        <input type="hidden" name="id" value="<%= p.getId() %>"/>

        <input type="text" name="name" value="<%= p.getName() %>" required/>
        <input type="text" name="category" value="<%= p.getCategory() %>" required/>
        <input type="number" name="quantity" value="<%= p.getQuantity() %>" required/>
        <input type="number" name="price" value="<%= p.getPrice() %>" required/>

        <button type="submit">Update Product</button>
    </form>

</div>

<jsp:include page="footer.jsp"/>

</body>
</html>