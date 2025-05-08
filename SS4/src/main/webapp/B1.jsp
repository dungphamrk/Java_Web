<%--
  Created by IntelliJ IDEA.
  User: dungp
  Date: 5/8/2025
  Time: 3:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>Danh sách sản phẩm</title>
    <style>
      table {
        border-collapse: collapse;
        width: 80%;
        margin: 20px auto;
      }
      th, td {
        border: 1px solid black;
        padding: 8px;
        text-align: left;
      }
      th {
        background-color: #f2f2f2;
      }
    </style>
  </head>
  <body>
    <h1 style="text-align: center;">Danh sách sản phẩm</h1>
    <table>
      <tr>
        <th>Tên</th>
        <th>Giá</th>
        <th>Mô tả</th>
      </tr>
      <c:forEach var="product" items="${products}">
        <tr>
          <td>${product.name}</td>
          <td>$${product.price}</td>
          <td>${product.description}</td>
        </tr>
      </c:forEach>
    </table>
  </body>
</html>
