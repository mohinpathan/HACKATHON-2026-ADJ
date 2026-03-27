<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.inventory.model.Product"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Inventory Dashboard</title>

<style>
body {
    margin: 0;
    font-family: 'Segoe UI', sans-serif;
    background: linear-gradient(120deg, #1e3c72, #2a5298);
    color: white;
}

/* SEARCH */
.search-box {
    margin: 20px;
    text-align: center;
}

input, select {
    padding: 10px;
    margin: 5px;
    border-radius: 8px;
    border: none;
}

/* REPORT */
.report-container {
    display: flex;
    justify-content: center;
    gap: 20px;
    margin: 20px;
}

.report-card {
    width: 200px;
    padding: 20px;
    border-radius: 15px;
    text-align: center;
    font-weight: bold;
    box-shadow: 0px 4px 15px rgba(0,0,0,0.3);
}

.total { background: #6a11cb; }
.instock { background: #00c853; }
.lowstock { background: #ff3d00; }

.report-card p {
    font-size: 28px;
}

/* CARDS */
.container {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    gap: 20px;
}

.card {
    position: relative;
    background: white;
    color: black;
    width: 220px;
    padding: 15px;
    border-radius: 15px;
    box-shadow: 0px 4px 15px rgba(0,0,0,0.2);
    transition: 0.3s;
}

.card:hover {
    transform: scale(1.05);
}

.low {
    border: 3px solid red;
}

/* PRODUCT ID */
.product-id {
    position: absolute;
    top: 10px;
    right: 10px;
    background: #2a5298;
    color: white;
    padding: 3px 8px;
    border-radius: 10px;
    font-size: 12px;
}

/* BUTTONS */
.btn-group {
    display: flex;
    justify-content: space-between;
    margin-top: 10px;
}

button {
    border: none;
    padding: 8px 10px;
    border-radius: 8px;
    cursor: pointer;
}

.delete-btn {
    background: #ff4d4d;
    color: white;
}

.edit-btn {
    background: #2a5298;
    color: white;
}
</style>

<script>
function filterProducts() {
    let search = document.getElementById("search").value.toLowerCase();
    let category = document.getElementById("category").value;

    let cards = document.getElementsByClassName("card");

    for (let i = 0; i < cards.length; i++) {
        let name = cards[i].getAttribute("data-name");
        let cat = cards[i].getAttribute("data-category");

        if ((name.includes(search)) &&
            (category === "all" || cat === category)) {
            cards[i].style.display = "block";
        } else {
            cards[i].style.display = "none";
        }
    }
}
</script>

</head>

<body>

<jsp:include page="header.jsp"/>

<!-- SEARCH -->
<div class="search-box">
    <input type="text" id="search" placeholder="Search..." onkeyup="filterProducts()"/>

    <select id="category" onchange="filterProducts()">
        <option value="all">All</option>
        <option value="charger">Charger</option>
        <option value="cable">Cable</option>
        <option value="audio">Audio</option>
        <option value="cover">Cover</option>
        <option value="battery">Battery</option>
    </select>
</div>

<%
List<Product> products = (List<Product>) request.getAttribute("productList");

int total = 0;
int inStock = 0;
int lowStock = 0;

if(products != null){
    total = products.size();

    for(Product p : products){
        if(p.getQuantity() <= p.getLowStockThreshold()){
            lowStock++;
        } else {
            inStock++;
        }
    }
}
%>

<!-- REPORT -->
<div class="report-container">
    <div class="report-card total">
        <h3>Total</h3>
        <p><%= total %></p>
    </div>

    <div class="report-card instock">
        <h3>In Stock</h3>
        <p><%= inStock %></p>
    </div>

    <div class="report-card lowstock">
        <h3>Low Stock</h3>
        <p><%= lowStock %></p>
    </div>
</div>

<!-- PRODUCTS -->
<div class="container">

<%
if(products != null){
    for(Product p : products){
        boolean low = p.getQuantity() <= p.getLowStockThreshold();
%>

<div class="card <%= low ? "low" : "" %>"
     data-name="<%= p.getName().toLowerCase() %>"
     data-category="<%= p.getCategory().toLowerCase() %>">

    <div class="product-id">ID: <%= p.getId() %></div>

    <h3><%= p.getName() %></h3>
    <p>Category: <%= p.getCategory() %></p>
    <p>₹<%= p.getPrice() %></p>
    <p>Qty: <%= p.getQuantity() %></p>

    <p style="color:<%= low ? "red" : "green" %>;">
        <%= low ? "Low Stock" : "In Stock" %>
    </p>

    <div class="btn-group">
        <form action="inventory" method="post">
            <input type="hidden" name="id" value="<%= p.getId() %>"/>
            <button name="action" value="delete" class="delete-btn">Delete</button>
        </form>

        <form action="editProduct.jsp" method="get">
            <input type="hidden" name="id" value="<%= p.getId() %>"/>
            <button class="edit-btn">Edit</button>
        </form>
    </div>

</div>

<%
    }
}
%>

</div>

<jsp:include page="footer.jsp"/>

</body>
</html>