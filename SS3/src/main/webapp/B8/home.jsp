<%--
  Created by IntelliJ IDEA.
  User: dungp
  Date: 5/7/2025
  Time: 4:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%-- home.jsp --%>
<%-- Đặt trong thư mục src/main/webapp --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="B8.Book" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Quản lý thư viện sách</title>
        <meta charset="UTF-8">
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
        <style>
            body {
                font-family: 'Roboto', Arial, sans-serif;
                line-height: 1.6;
                margin: 20px;
            }
            .container {
                max-width: 800px;
                margin: 0 auto;
            }
            .form-group {
                margin-bottom: 15px;
            }
            label {
                display: inline-block;
                width: 150px;
            }
            input, button {
                padding: 5px;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
            }
            th, td {
                border: 1px solid #ddd;
                padding: 8px;
                text-align: left;
            }
            th {
                background-color: #f2f2f2;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h2>Quản lý thư viện sách</h2>

            <!-- Form thêm sách mới -->
            <h3>Thêm sách mới</h3>
            <form action="BookServlet" method="post">
                <input type="hidden" name="action" value="add">
                <div class="form-group">
                    <label for="title">Tên sách:</label>
                    <input type="text" id="title" name="title" required>
                </div>
                <div class="form-group">
                    <label for="author">Tác giả:</label>
                    <input type="text" id="author" name="author" required>
                </div>
                <div class="form-group">
                    <label for="year">Năm xuất bản:</label>
                    <input type="number" id="year" name="year" required>
                </div>
                <button type="submit">Thêm sách</button>
            </form>

            <!-- Form tìm kiếm sách -->
            <h3>Tìm kiếm sách</h3>
            <form action="BookServlet" method="get">
                <input type="hidden" name="action" value="search">
                <div class="form-group">
                    <label for="searchTitle">Tìm theo tên sách:</label>
                    <input type="text" id="searchTitle" name="searchTitle">
                </div>
                <button type="submit">Tìm kiếm</button>
            </form>

            <!-- Hiển thị danh sách sách -->
            <h3>Danh sách sách</h3>
            <%
                List<Book> bookList = (List<Book>) request.getServletContext().getAttribute("bookList");
                List<Book> filteredBooks = (List<Book>) request.getAttribute("filteredBooks");
                List<Book> displayList = (filteredBooks != null && !filteredBooks.isEmpty()) ? filteredBooks : bookList;
            %>
            <% if (displayList != null && !displayList.isEmpty()) { %>
            <table>
                <tr>
                    <th>Tên sách</th>
                    <th>Tác giả</th>
                    <th>Năm xuất bản</th>
                </tr>
                <% for (Book book : displayList) { %>
                <tr>
                    <td><%= book.getTitle() %></td>
                    <td><%= book.getAuthor() %></td>
                    <td><%= book.getYear() %></td>
                </tr>
                <% } %>
            </table>
            <% } else { %>
            <p>Chưa có sách nào trong thư viện.</p>
            <% } %>
        </div>
    </body>
</html>
