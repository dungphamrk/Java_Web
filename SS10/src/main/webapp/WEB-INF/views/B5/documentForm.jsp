<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Upload Document</title>
  </head>
  <body>
    <div class="form-container">
      <h2>Upload Document</h2>
      <form:form method="post" action="/B5/document/uploadDocument"
                 modelAttribute="document" enctype="multipart/form-data">
        <div class="form-group">
          <label for="title">Title:</label>
          <form:input path="title" id="title" required="true"/>
          <form:errors path="title" cssClass="error"/>
        </div>
        <div class="form-group">
          <label for="description">Description:</label>
          <form:textarea path="description" id="description" required="true"/>
          <form:errors path="description" cssClass="error"/>
        </div>
        <div class="form-group">
          <label for="file">Document File:</label>
          <form:input type="file" path="file" id="file" required="true"/>
          <form:errors path="file" cssClass="error"/>
        </div>
        <input type="submit" value="Upload"/>
      </form:form>

      <c:if test="${not empty message}">
        <div class="${message.contains('thành công') ? 'message' : 'error'}">
            ${message}
        </div>
      </c:if>
      <c:if test="${not empty filePath}">
        <div class="uploaded-info">
          <h3>Uploaded Document:</h3>
          <p><strong>Title:</strong> ${title}</p>
          <p><strong>Description:</strong> ${description}</p>
          <p><strong>File:</strong> <a href="${filePath}" target="_blank">View Document</a></p>
        </div>
      </c:if>
    </div>
  </body>
</html>