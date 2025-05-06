<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, org.example.ss2.Product" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Product List</title>
        <style>
            table {
                border-collapse: collapse;
                width: 100%;
                margin-top: 20px;
            }
            th, td {
                border: 1px solid black;
                padding: 8px;
                text-align: left;
            }
            th {
                background-color: #f2f2f2;
            }
            .form-container {
                margin-top: 20px;
            }
            .action-btn {
                margin-right: 5px;
            }
            .success {
                color: green;
            }
        </style>
    </head>
    <body>
        <h2>Danh Sách Sản Phẩm</h2>

        <!-- Success Message -->
        <%
            String success = (String) request.getAttribute("success");
            if (success != null) {
        %>
        <p class="success"><%= success %></p>
        <%
            }
        %>

        <!-- Product List Table -->
        <table>
            <tr>
                <th>ID</th>
                <th>Tên Sản Phẩm</th>
                <th>Giá</th>
                <th>Hành Động</th>
            </tr>
            <%
                List<Product> products = (List<Product>) request.getAttribute("products");
                if (products != null) {
                    for (Product product : products) {
            %>
            <tr>
                <td><%= product.getId() %></td>
                <td><%= product.getName() %></td>
                <td><%= String.format("%.2f", product.getPrice()) %></td>
                <td>
                    <form action="ProductServlet" method="get" style="display:inline;">
                        <input type="hidden" name="action" value="edit">
                        <input type="hidden" name="id" value="<%= product.getId() %>">
                        <button type="submit" class="action-btn">Sửa</button>
                    </form>
                    <form action="ProductServlet" method="post" style="display:inline;">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="id" value="<%= product.getId() %>">
                        <button type="submit" class="action-btn">Xóa</button>
                    </form>
                </td>
            </tr>
            <%
                    }
                }
            %>
        </table>

        <!-- Add Product Form -->
        <div class="form-container">
            <h3>Thêm Sản Phẩm Mới</h3>
            <form action="ProductServlet" method="post">
                <label for="name">Tên Sản Phẩm:</label><br>
                <input type="text" id="name" name="name" required><br><br>
                <label for="price">Giá:</label><br>
                <input type="number" id="price" name="price" step="0.01" required><br><br>
                <button type="submit">Thêm Sản Phẩm</button>
            </form>
            <%
                String error = (String) request.getAttribute("error");
                if (error != null) {
            %>
            <p style="color: red;"><%= error %></p>
            <%
                }
            %>
        </div>
    </body>
</html>