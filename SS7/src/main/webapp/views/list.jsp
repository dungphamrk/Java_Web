<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 13/05/2025
  Time: 17:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Danh sách góp ý</h2>
<table border="1">
    <tr>
        <th>Họ tên</th>
        <th>SĐT</th>
        <th>Địa chỉ</th>
        <th>Nội dung</th>
    </tr>
    <c:forEach var="f" items="${feedbackList}">
        <tr>
            <td>${f.name}</td>
            <td>${f.phone}</td>
            <td>${f.address}</td>
            <td>${f.content}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
