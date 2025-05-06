<%--
  Created by IntelliJ IDEA.
  User: dungp
  Date: 5/6/2025
  Time: 8:38 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Giải thích vai trò các thư mục</title>
    <style>
      body {
        font-family: Arial, sans-serif;
        padding: 20px;
      }
      h2 {
        color: #2c3e50;
        text-align: center;
      }
      table {
        width: 90%;
        margin: 20px auto;
        border-collapse: collapse;
      }
      th, td {
        border: 1px solid #999;
        padding: 10px;
        text-align: left;
      }
      th {
        background-color: #e0e0e0;
      }
    </style>
  </head>
  <body>

    <h2>Giải thích vai trò của từng thư mục</h2>
    <table>
      <tr>
        <th>Thư mục</th>
        <th>Vai trò</th>
      </tr>
      <tr>
        <td>src/main/java</td>
        <td>Chứa toàn bộ mã nguồn Java. Đây là nơi bạn viết các lớp controller, model, service, servlet,...</td>
      </tr>
      <tr>
        <td>src/main/resources</td>
        <td>Chứa các file cấu hình như <code>application.properties</code>, file cấu trúc SQL, file messages quốc tế hóa,...</td>
      </tr>
      <tr>
        <td>src/main/webapp</td>
        <td>Chứa tài nguyên web, bao gồm HTML, JSP, CSS, JS. Đây là thư mục gốc được deploy lên server.</td>
      </tr>
    </table>

  </body>
</html>
