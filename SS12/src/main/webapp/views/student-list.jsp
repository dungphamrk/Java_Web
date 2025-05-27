<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>Danh sách sinh viên</title></head>
<body>
<a href="${pageContext.request.contextPath}/students/add">Thêm sinh viên mới</a>
<table border="1">
  <tr>
    <th>ID</th>
    <th>Tên</th>
    <th>Email</th>
    <th>Ngày sinh</th>
    <th>Hành động</th>
  </tr>
  <c:forEach var="s" items="${students}">
    <tr>
      <td>${s.id}</td>
      <td>${s.name}</td>
      <td>${s.email}</td>
      <td>${s.dob}</td>
      <td>
        <a href="/students/edit/${s.id}">Sửa</a>
        <a href="/students/delete/${s.id}" onclick="return confirm('Bạn chắc chắn muốn xóa?')">Xóa</a>
      </td>
    </tr>
  </c:forEach>
</table>
</body>
</html>

