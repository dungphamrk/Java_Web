<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Add Category</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container">
    <h2>Add New Category</h2>
    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>
    <form:form action="${pageContext.request.contextPath}/addCategory" method="post" modelAttribute="category">
        <div class="form-group">
            <label for="categoryName">Category Name:</label>
            <form:input path="categoryName" id="categoryName"/>
            <form:errors path="categoryName" cssClass="error"/>
        </div>
        <div class="form-group">
            <label for="status">Status:</label>
            <form:checkbox path="status" id="status" checked="checked"/>
        </div>
        <button type="submit">Add Category</button>
    </form:form>
    <a href="${pageContext.request.contextPath}/categories">Back to List</a>
</div>
</body>
</html>