
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>Danh sách xe khách</title></head>
<body>
<a href="${pageContext.request.contextPath}/bus/add">Thêm xe mới</a>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Biển số</th>
        <th>Loại xe</th>
        <th>Số hàng</th>
        <th>Số cột</th>
        <th>Tổng ghế</th>
        <th>Ảnh</th>
        <th>Hành động</th>
    </tr>
    <c:forEach var="b" items="${buses}">
        <tr>
            <td>${b.id}</td>
            <td>${b.licensePlate}</td>
            <td>${b.busType}</td>
            <td>${b.rowSeat}</td>
            <td>${b.colSeat}</td>
            <td>${b.totalSeat}</td>
            <td>
                <c:if test="${b.image != null}">
                    <img src="${b.image}" width="80"/>
                </c:if>
            </td>
            <td>
                <a href="/bus/edit/${b.id}">Sửa</a>
                <a href="/bus/delete/${b.id}" onclick="return confirm('Bạn chắc chắn muốn xóa?')">Xóa</a>
                <a href="/bus/detail/${b.id}">Chi tiết ghế</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>

