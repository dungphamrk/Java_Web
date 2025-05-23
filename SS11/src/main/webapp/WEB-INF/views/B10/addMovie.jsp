<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Add Movie</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container">
    <h2>Add New Movie</h2>
    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>
    <form:form action="${pageContext.request.contextPath}/addMovie" method="post" modelAttribute="movie" enctype="multipart/form-data">
        <div class="form-group">
            <label for="title">Title:</label>
            <form:input path="title" id="title"/>
            <form:errors path="title" cssClass="error"/>
        </div>
        <div class="form-group">
            <label for="director">Director:</label>
            <form:input path="director" id="director"/>
            <form:errors path="director" cssClass="error"/>
        </div>
        <div class="form-group">
            <label for="releaseDate">Release Date:</label>
            <form:input path="releaseDate" id="releaseDate" type="date"/>
            <form:errors path="releaseDate" cssClass="error"/>
        </div>
        <div class="form-group">
            <label for="genre">Genre:</label>
            <form:input path="genre" id="genre"/>
            <form:errors path="genre" cssClass="error"/>
        </div>
        <div class="form-group">
            <label for="posterFile">Poster:</label>
            <form:input path="posterFile" id="posterFile" type="file" accept="image/*"/>
            <form:errors path="posterFile" cssClass="error"/>
        </div>
        <button type="submit">Add Movie</button>
    </form:form>
    <a href="${pageContext.request.contextPath}/movies">Back to List</a>
</div>
</body>
</html>