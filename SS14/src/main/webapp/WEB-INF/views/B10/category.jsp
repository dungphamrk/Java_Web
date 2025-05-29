<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title><spring:message code="title"/></title></head>
<body>
<h2><spring:message code="title"/></h2>
<form:form method="POST" action="/category/add" modelAttribute="categoryForm">
  <div>
    <label><spring:message code="category.name.vi"/>:</label>
    <form:input path="categoryNameVi"/>
  </div>
  <div>
    <label><spring:message code="category.desc.vi"/>:</label>
    <form:input path="descriptionVi"/>
  </div>
  <div>
    <label><spring:message code="category.name.en"/>:</label>
    <form:input path="categoryNameEn"/>
  </div>
  <div>
    <label><spring:message code="category.desc.en"/>:</label>
    <form:input path="descriptionEn"/>
  </div>
  <button type="submit"><spring:message code="submit"/></button>
</form:form>

<h3><spring:message code="category.list"/></h3>
<table border="1">
  <tr>
    <th><spring:message code="category.name.vi"/></th>
    <th><spring:message code="category.desc.vi"/></th>
  </tr>
  <c:forEach var="cat" items="${categories}">
    <tr>
      <td>${cat.categoryName}</td>
      <td>${cat.description}</td>
    </tr>
  </c:forEach>
</table>

<a href="?lang=vi">Tiếng Việt</a> | <a href="?lang=en">English</a>
</body>
</html>