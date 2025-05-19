<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Upload Avatar</title>
    </head>
    <body>
        <div class="form-container">
            <h2>Upload Your Avatar</h2>
            <form method="post" action="/B4/uploadAvatar" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="file">Avatar:</label>
                    <input type="file" name="file" id="file" accept="image/*" required>
                </div>
                <input type="submit" value="Upload"/>
            </form>

            <c:if test="${not empty message}">
                <div class="${message.contains('thành công') ? 'message' : 'error'}">
                        ${message}
                </div>
            </c:if>
        </div>
    </body>
</html>