
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head><title>Thêm/Sửa Xe khách</title></head>
<body>
<form:form method="POST" modelAttribute="bus" enctype="multipart/form-data">
  <div>
    <label>Biển số xe:</label>
    <form:input path="licensePlate"/>
    <form:errors path="licensePlate" cssClass="error"/>
  </div>
  <div>
    <label>Loại xe:</label>
    <form:select path="busType">
      <form:option value="">--Chọn loại--</form:option>
      <form:option value="NORMAL">NORMAL</form:option>
      <form:option value="VIP">VIP</form:option>
      <form:option value="LUXURY">LUXURY</form:option>
    </form:select>
    <form:errors path="busType" cssClass="error"/>
  </div>
  <div>
    <label>Số hàng ghế:</label>
    <form:input path="rowSeat" type="number"/>
    <form:errors path="rowSeat" cssClass="error"/>
  </div>
  <div>
    <label>Số cột ghế:</label>
    <form:input path="colSeat" type="number"/>
    <form:errors path="colSeat" cssClass="error"/>
  </div>
  <div>
    <label>Ảnh xe:</label>
    <input type="file" name="imageFile"/>
    <c:if test="${bus.image != null}">
      <img src="${bus.image}" width="100"/>
    </c:if>
    <form:errors path="image" cssClass="error"/>
  </div>
  <button type="submit">Lưu</button>
</form:form>
<a href="${pageContext.request.contextPath}/bus">Quay lại danh sách</a>
</body>
</html>

