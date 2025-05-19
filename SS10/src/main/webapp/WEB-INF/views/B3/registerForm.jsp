<%--
  Created by IntelliJ IDEA.
  User: dungp
  Date: 5/18/2025
  Time: 9:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>
        <title>B3</title>
    </head>
    <body>
        <%--@elvariable id="account" type="com.data.ss10.model.Account"--%>
        <form:form action="submit" method="post" modelAttribute="newAccount">
            Tên đăng nhập: <form:input path="username"/><br>
            Mật khẩu: <form:input path="password"/><br>
            Mail: <form:input path="email"/> <br>
            <input type="submit" value="Gửi">
        </form:form>
    </body>
</html>
