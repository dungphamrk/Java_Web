<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Category List</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container">
    <h2>Category List</h2>
    <a href="${pageContext.request.contextPath}/addCategory">Add New Category</a>
    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Status</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="category" items="${categories}">
            <tr>
                <td>${category.id}</td>
                <td>${category.categoryName}</td>
                <td>${category.status ? 'Active' : 'Inactive'}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/editCategory?id=${category.id}">Edit</a>
                    <a href="${pageContext.request.contextPath}/deleteCategory?id=${category.id}"
                       onclick="return confirm('Are you sure you want to delete this category?')">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>