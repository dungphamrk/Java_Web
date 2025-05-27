
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>Danh sách sản phẩm</title></head>
<body>
<form action="${pageContext.request.contextPath}/products-practice/search" method="get">
  <input type="text" name="name" placeholder="Tìm kiếm sản phẩm..."/>
  <button type="submit">Tìm kiếm</button>
</form>
<a href="${pageContext.request.contextPath}/products-practice/add">Thêm sản phẩm mới</a>
<table border="1">
  <tr>
    <th>ID</th>
    <th>Tên</th>
    <th>Trạng thái</th>
    <th>Ảnh</th>
    <th>Hành động</th>
  </tr>
  <c:forEach var="p" items="${products}">
    <tr>
      <td>${p.id}</td>
      <td>${p.name}</td>
      <td>
        <c:choose>
          <c:when test="${p.status}">Kích hoạt</c:when>
          <c:otherwise>Ẩn</c:otherwise>
        </c:choose>
      </td>
      <td>
        <c:if test="${p.image != null}">
          <img src="${p.image}" width="80"/>
        </c:if>
      </td>
      <td>
        <a href="/products-practice/edit/${p.id}">Sửa</a>
        <a href="/products-practice/delete/${p.id}" onclick="return confirm('Bạn chắc chắn muốn xóa?')">Xóa</a>
      </td>
    </tr>
  </c:forEach>
</table>
</body>
</html>

