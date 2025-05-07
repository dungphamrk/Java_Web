<%--
  Created by IntelliJ IDEA.
  User: dungp
  Date: 5/7/2025
  Time: 2:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>Thêm sản phẩm</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
  </head>
  <body>
    <h2>Thêm sản phẩm mới</h2>
    <form action="../addProduct" method="post">
      <label>ID: </label><input type="text" name="id" required><br><br>
      <label>Tên sản phẩm: </label><input type="text" name="productName" required><br><br>
      <label>Giá: </label><input type="number" name="price" step="0.01" required><br><br>
      <label>Mô tả: </label><input type="text" name="description"><br><br>
      <label>Số lượng: </label><input type="number" name="stock" required><br><br>
      <label>Trạng thái: </label>
      <select name="status">
        <option value="true">Còn hàng</option>
        <option value="false">Hết hàng</option>
      </select><br><br>
      <input type="submit" value="Thêm sản phẩm">
    </form>
    <br>
    <a href="../displayProducts">Xem danh sách sản phẩm</a>
  </body>
</html>