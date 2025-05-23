<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>B1</title>
        <style>
            .error { color: red; }
            .success { color: green; }
        </style>
    </head>
    <body>
        <div class="form-container">
            <h2>Form Thông Tin Người Dùng</h2>

            <c:if test="${not empty message}">
                <p class="success">${message}</p>
            </c:if>

            <%--@elvariable id="user" type="com.data.ss11.model.User"--%>
            <form:form modelAttribute="user" method="post" action="userForm">
                <div class="form-group">
                    <label for="name">Tên:</label>
                    <form:input path="name" id="name"/>
                    <form:errors path="name" cssClass="error"/>
                </div>

                <div class="form-group">
                    <label for="email">Email:</label>
                    <form:input path="email" id="email"/>
                    <form:errors path="email" cssClass="error"/>
                </div>

                <div class="form-group">
                    <label for="phone">Số điện thoại:</label>
                    <form:input path="phone" id="phone"/>
                    <form:errors path="phone" cssClass="error"/>
                </div>

                <div class="form-group">
                    <label for="password">Mật khẩu:</label>
                    <form:password path="password" id="password"/>
                    <form:errors path="password" cssClass="error"/>
                </div>

                <div class="form-group">
                    <label for="status">Trạng thái:</label>
                    <form:select path="status" id="status">
                        <form:option value="" label="Chọn"/>
                        <form:option value="true" label="Hoạt động"/>
                        <form:option value="false" label="Không hoạt động"/>
                    </form:select>
                    <form:errors path="status" cssClass="error"/>
                </div>

                <div class="form-group">
                    <input type="submit" value="Gửi"/>
                </div>
            </form:form>
        </div>
    </body>
</html>
