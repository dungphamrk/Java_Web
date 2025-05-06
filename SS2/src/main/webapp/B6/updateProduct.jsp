<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.example.ss2.Product" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Update Product</title>
        <style>
            .form-container {
                margin-top: 20px;
                max-width: 400px;
            }
            .error {
                color: red;
            }
        </style>
    </head>
    <body>
        <h2>Cập Nhật Sản Phẩm</h2>

        <!-- Error Message -->
        <%
            String error = (String) request.getAttribute("error");
            if (error != null) {
        %>
        <p class="error"><%= error %></p>
        <%
            }
        %>

        <!-- Update Product Form -->
        <%
            Product product = (Product) request.getAttribute("editProduct");
            if (product != null) {
        %>
        <div class="form-container">
            <form action="ProductServlet" method="post">
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="id" value="<%= product.getId() %>">
                <label for="name">Tên Sản Phẩm:</label><br>
                <input type="text" id="name" name="name" value="<%= product.getName() %>" required><br><br>
                <label for="price">Giá:</label><br>
                <input type="number" id="price" name="price" step="0.01" value="<%= product.getPrice() %>" required><br><br>
                <button type="submit">Cập Nhật Sản Phẩm</button>
            </form>
            <p><a href="ProductServlet">Quay lại danh sách sản phẩm</a></p>
        </div>
        <%
        } else {
        %>
        <p>Không tìm thấy sản phẩm để cập nhật.</p>
        <p><a href="ProductServlet">Quay lại danh sách sản phẩm</a></p>
        <%
            }
        %>
    </body>
</html>