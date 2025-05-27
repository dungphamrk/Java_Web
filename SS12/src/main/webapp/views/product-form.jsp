<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>Thêm/Sửa Sản phẩm</title></head>
<body>
<form:form method="POST" modelAttribute="product" enctype="multipart/form-data">
  <div>
    <label>Tên sản phẩm:</label>
    <form:input path="name"/>
    <form:errors path="name" cssClass="error"/>
  </div>
  <div>
    <label>Giá:</label>
    <form:input path="price"/>
    <form:errors path="price" cssClass="error"/>
  </div>
  <div>
    <label>Số lượng:</label>
    <form:input path="quantity"/>
    <form:errors path="quantity" cssClass="error"/>
  </div>
  <div>
    <label>Ảnh sản phẩm:</label>
    <input type="file" name="imageFile"/>
    <c:if test="${product.image != null}">
      <img src="${product.image}" width="80"/>
    </c:if>
    <form:errors path="image" cssClass="error"/>
  </div>
  <button type="submit">Lưu</button>
</form:form>
<a href="${pageContext.request.contextPath}/products">Quay lại danh sách</a>
</body>
</html>

