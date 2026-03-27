<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Stock</title>

<style>
body {
    background: linear-gradient(120deg, #1e3c72, #2a5298);
    font-family: Arial;
}

.card {
    width: 350px;
    margin: 100px auto;
    padding: 20px;
    background: white;
    border-radius: 15px;
    text-align: center;
}

input {
    width: 90%;
    padding: 10px;
    margin: 10px;
}

button {
    padding: 10px;
    margin: 5px;
    border: none;
    color: white;
    border-radius: 8px;
}

.in { background: green; }
.out { background: red; }
</style>
</head>

<body>
<script>
function fetchProduct() {

    let id = document.getElementById("pid").value;

    if (id === "") return;

    fetch("inventory?action=getProduct&id=" + id)
        .then(res => res.json())
        .then(data => {

            if (data.error) {
                document.getElementById("pname").value = "Not Found";
                document.getElementById("pqty").value = "";
            } else {
                document.getElementById("pname").value = data.name;
                document.getElementById("pqty").value = data.qty;
            }
        });
}
</script>
<jsp:include page="header.jsp"/>

<div class="card">
<h2>Stock Management</h2>

<form action="inventory" method="post">

    <input type="number" id="pid" name="id" placeholder="Product ID"
           onkeyup="fetchProduct()" required/>

    <input type="text" id="pname" placeholder="Product Name" readonly/>

    <input type="number" id="pqty" placeholder="Current Quantity" readonly/>

    <input type="number" name="quantity" placeholder="Enter Quantity" min="1" required/>

    <button class="in" name="action" value="stockIn">Stock In</button>
    <button class="out" name="action" value="stockOut">Stock Out</button>

</form>
</div>

<jsp:include page="footer.jsp"/>

</body>
</html>