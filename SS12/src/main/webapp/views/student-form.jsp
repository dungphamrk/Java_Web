<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head><title>Thêm/Sửa Sinh viên</title></head>
<body>
<form:form method="POST" modelAttribute="student">
  <div>
    <label>Tên:</label>
    <form:input path="name"/>
    <form:errors path="name" cssClass="error"/>
  </div>
  <div>
    <label>Email:</label>
    <form:input path="email"/>
    <form:errors path="email" cssClass="error"/>
  </div>
  <div>
    <label>Ngày sinh:</label>
    <form:input path="dob" type="date"/>
    <form:errors path="dob" cssClass="error"/>
  </div>
  <button type="submit">Lưu</button>
</form:form>
<a href="${pageContext.request.contextPath}/students">Quay lại danh sách</a>
</body>
</html>
