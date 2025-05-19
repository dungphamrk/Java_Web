<%--
  Created by IntelliJ IDEA.
  User: dungp
  Date: 5/16/2025
  Time: 5:28 PM
  To change this template use File | Settings | File Templates.
--%><%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
  <head>
    <title>B1</title>
  </head>
  <body>
    <%--@elvariable id="product" type="com.data.ss10.model.Product"--%>
    <form:form action="submit" method="post" modelAttribute="newProduct">
      Tên: <form:input path="name"/><br>
      Mô tả: <form:input path="description"/><br>
      Giá: <form:input path="price"/> <br>
      <input type="submit" value="Gửi">
    </form:form>
  </body>
</html>
