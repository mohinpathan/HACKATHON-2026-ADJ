<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<style>
.header {
    background: rgba(0,0,0,0.7);
    backdrop-filter: blur(10px);
    padding: 15px;
    display: flex;
    justify-content: space-between;
    align-items: center;
    color: white;
}

.logo {
    font-size: 20px;
    font-weight: bold;
}

.nav a {
    margin: 0 10px;
    color: white;
    text-decoration: none;
    transition: 0.3s;
}

.nav a:hover {
    color: #00e5ff;
}
</style>

<div class="header">
    <div class="logo">📱 Inventory</div>
    <div class="nav">
        <a href="home">Dashboard</a>
        <a href="addProduct.jsp">Add Product</a>
        <a href="stock.jsp">Stock</a>
        <a href="sales.jsp">Sales</a>
    </div>
</div>