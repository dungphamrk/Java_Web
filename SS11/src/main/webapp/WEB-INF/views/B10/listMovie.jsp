<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Movie List</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container">
  <h2>Movie List</h2>
  <a href="${pageContext.request.contextPath}/addMovie">Add New Movie</a>
  <c:if test="${not empty error}">
    <div class="error">${error}</div>
  </c:if>
  <table>
    <thead>
    <tr>
      <th>ID</th>
      <th>Title</th>
      <th>Director</th>
      <th>Release Date</th>
      <th>Genre</th>
      <th>Poster</th>
      <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="movie" items="${movies}">
      <tr>
        <td>${movie.id}</td>
        <td>${movie.title}</td>
        <td>${movie.director}</td>
        <td>${movie.releaseDate}</td>
        <td>${movie.genre}</td>
        <td><img src="${movie.poster}" alt="${movie.title}" style="width:100px;"></td>
        <td>
          <a href="${pageContext.request.contextPath}/editMovie?id=${movie.id}">Edit</a>
          <a href="${pageContext.request.contextPath}/deleteMovie?id=${movie.id}"
             onclick="return confirm('Are you sure you want to delete this movie?')">Delete</a>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>
</body>
</html>