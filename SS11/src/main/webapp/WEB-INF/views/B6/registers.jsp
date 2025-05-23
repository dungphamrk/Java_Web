<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Đăng ký</title>
    <style>.error { color: red; }</style>
</head>
<body>
<h2>Đăng ký người dùng</h2>

<form:form method="post" modelAttribute="userForm">
    <div>
        <label>Số điện thoại:</label>
        <form:input path="phoneNumber"/>
        <form:errors path="phoneNumber" cssClass="error"/>
    </div>

    <button type="submit">Gửi</button>
</form:form>
</body>
</html>
