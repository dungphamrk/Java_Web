<%--
  Created by IntelliJ IDEA.
  User: dungp
  Date: 5/7/2025
  Time: 3:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%-- orderForm.jsp --%>
<%-- Đặt trong thư mục src/main/webapp --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Nhập thông tin đơn hàng</title>
        <meta charset="UTF-8">
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
        <style>
            body {
                font-family: 'Roboto', Arial, sans-serif;
                line-height: 1.6;
                margin: 20px;
            }
            .form-container {
                max-width: 600px;
                margin: 0 auto;
            }
            .form-group {
                margin-bottom: 15px;
            }
            label {
                display: inline-block;
                width: 150px;
            }
            input, button {
                padding: 5px;
            }
        </style>
    </head>
    <body>
        <div class="form-container">
            <h2>Nhập thông tin đơn hàng</h2>
            <form action="../ProcessOrderServlet" method="post">
                <div class="form-group">
                    <label for="productName">Sản phẩm:</label>
                    <input type="text" id="productName" name="productName" required>
                </div>
                <div class="form-group">
                    <label for="quantity">Số lượng:</label>
                    <input type="number" id="quantity" name="quantity" min="1" required>
                </div>
                <div class="form-group">
                    <label for="price">Giá mỗi sản phẩm:</label>
                    <input type="number" id="price" name="price" step="0.01" min="0" required>
                </div>
                <button type="submit">Thêm vào đơn hàng</button>
            </form>
            <br>
            <a href="viewOrder.jsp">Xem đơn hàng</a>
        </div>
    </body>
</html>