<%--
  Created by IntelliJ IDEA.
  User: dungp
  Date: 5/8/2025
  Time: 11:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>Danh Sách Doanh Thu</title>
    <style>
      body {
        font-family: Arial, sans-serif;
        margin: 20px;
        text-align: center;
      }
      table {
        border-collapse: collapse;
        width: 60%;
        margin: 20px auto;
      }
      th, td {
        border: 1px solid black;
        padding: 10px;
        text-align: center;
      }
      th {
        background-color: #f2f2f2;
      }
      .message {
        font-weight: bold;
        margin-top: 20px;
      }
      .success {
        color: green;
      }
      .warning {
        color: red;
      }
    </style>
  </head>
  <body>
    <h2>Danh Sách Doanh Thu</h2>

    <!-- Bảng hiển thị doanh thu -->
    <table>
      <tr>
        <th>Tháng</th>
        <th>Doanh Thu ($)</th>
      </tr>
      <!-- Biến tính tổng doanh thu -->
      <c:set var="totalRevenue" value="0" scope="page"/>

      <!-- Lặp qua danh sách doanh thu -->
      <c:forEach var="revenue" items="${revenues}">
        <tr>
          <td>${revenue.month}</td>
          <td>${revenue.amount}</td>
        </tr>
        <!-- Cộng dồn doanh thu -->
        <c:set var="totalRevenue" value="${totalRevenue + revenue.amount}" scope="page"/>
      </c:forEach>
    </table>

    <!-- Hiển thị tổng doanh thu -->
    <p>Tổng doanh thu: ${totalRevenue} $</p>

    <!-- Kiểm tra điều kiện tổng doanh thu -->
    <c:choose>
      <c:when test="${totalRevenue > 10000}">
        <p class="message success">Tổng doanh thu vượt mốc 10,000 $!</p>
      </c:when>
      <c:otherwise>
        <p class="message warning">Tổng doanh thu chưa đạt 10,000 $.</p>
      </c:otherwise>
    </c:choose>
  </body>
</html>
