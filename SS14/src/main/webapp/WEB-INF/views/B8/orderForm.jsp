<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Đặt hàng</title></head>
<body>
<h2>Form đặt hàng</h2>
<form:form method="post" modelAttribute="order">
  Tên người dùng: <form:input path="username"/><br/>
  Sản phẩm: <form:input path="product"/><br/>
  Số lượng: <form:input path="quantity"/><br/>
  <input type="submit" value="Đặt hàng"/>
</form:form>
</body>
</html>
