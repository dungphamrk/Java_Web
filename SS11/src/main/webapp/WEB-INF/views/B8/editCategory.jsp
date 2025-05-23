<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Edit Category</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container">
    <h2>Edit Category</h2>
    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>
    <form:form action="${pageContext.request.contextPath}/editCategory" method="post" modelAttribute="category">
        <form:hidden path="id"/>
        <div class="form-group">
            <label for="categoryName">Category Name:</label>
            <form:input path="categoryName" id="categoryName"/>
            <form:errors path="categoryName" cssClass="error"/>
        </div>
        <div class="form-group">
            <label for="status">Status:</label>
            <form:checkbox path="status" id="status"/>
        </div>
        <button type="submit">Update Category</button>
    </form:form>
    <a href="${pageContext.request.contextPath}/categories">Back to List</a>
</div>
</body>
</html>