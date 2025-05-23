<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Đăng ký người dùng</title>
    <style>
        .error { color: red; }
        .hidden { display: none; }
    </style>
    <script>
        function toggleStaffCode() {
            var role = document.getElementById("role").value;
            document.getElementById("staffCodeDiv").style.display =
                (role === 'admin') ? 'block' : 'none';
        }
    </script>
</head>
<body onload="toggleStaffCode()">
<h2>Đăng ký tài khoản</h2>

<!-- Đổi action về /bt5/register -->
<form:form method="post" modelAttribute="form" action="${pageContext.request.contextPath}/bt5/register">
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
        <label>Vai trò:</label>
        <form:select path="role" id="role" onchange="toggleStaffCode()">
            <form:option value="">-- Chọn vai trò --</form:option>
            <form:option value="user">Người dùng</form:option>
            <form:option value="admin">Admin</form:option>
        </form:select>
        <form:errors path="role" cssClass="error"/>
    </div>

    <div id="staffCodeDiv" class="hidden">
        <label>Mã nhân viên:</label>
        <form:input path="staffCode"/>
        <form:errors path="staffCode" cssClass="error"/>
    </div>

    <button type="submit">Đăng ký</button>

    <div style="color:green;">
            ${message}
    </div>
</form:form>
</body>
</html>
