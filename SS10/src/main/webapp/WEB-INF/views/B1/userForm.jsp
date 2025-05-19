<%--
  Created by IntelliJ IDEA.
  User: dungp
  Date: 5/16/2025
  Time: 4:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>
        <title>B1</title>
    </head>
    <body>
        <%--@elvariable id="user" type="com.data.ss10.model.User"--%>
        <form:form action="submit" method="post" modelAttribute="user">
            Tên: <form:input path="name"/><br>
            Địa chỉ: <form:input path="address"/><br>
            Tuổi: <form:input path="age"/> <br>
            <input type="submit" value="Gửi">
        </form:form>
    </body>
</html>
