<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Đơn hàng</title></head>
<body>
<c:if test="${not empty order}">
  <h2>Thông tin đơn hàng</h2>
  Tên người dùng: ${order.username}<br/>
  Sản phẩm: ${order.product}<br/>
  Số lượng: ${order.quantity}<br/>
</c:if>
<c:if test="${empty order}">
  <h2>${message}</h2>
</c:if>
<a href="${pageContext.request.contextPath}/B8/order">Đặt hàng mới</a>
</body>
</html>
