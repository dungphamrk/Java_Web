
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>Danh sách sản phẩm</title></head>
<body>
<a href="${pageContext.request.contextPath}/products/add">Thêm sản phẩm mới</a>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Tên</th>
        <th>Giá</th>
        <th>Số lượng</th>
        <th>Ảnh</th>
        <th>Hành động</th>
    </tr>
    <c:forEach var="p" items="${products}">
        <tr>
            <td>${p.id}</td>
            <td>${p.name}</td>
            <td>${p.price}</td>
            <td>${p.quantity}</td>
            <td>
                <c:if test="${p.image != null}">
                    <img src="${p.image}" width="80"/>
                </c:if>
            </td>
            <td>
                <a href="/products/edit/${p.id}">Sửa</a>
                <a href="/products/delete/${p.id}" onclick="return confirm('Bạn chắc chắn muốn xóa?')">Xóa</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
