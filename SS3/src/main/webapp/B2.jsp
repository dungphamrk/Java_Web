<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Quản lý thông tin người dùng</title>
        <meta charset="UTF-8">
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
        <style>
            body {
                font-family: 'Roboto', Arial, sans-serif;
                line-height: 1.6;
                margin: 20px;
            }
            .container {
                max-width: 600px;
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
            .user-info {
                margin-top: 20px;
                border: 1px solid #ddd;
                padding: 10px;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h2>Nhập thông tin người dùng</h2>
            <form method="post">
                <div class="form-group">
                    <label for="name">Tên:</label>
                    <input type="text" id="name" name="name" required>
                </div>
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" required>
                </div>
                <button type="submit">Lưu thông tin</button>
            </form>

            <%
                // Xử lý khi form được gửi
                if ("POST".equalsIgnoreCase(request.getMethod())) {
                    String name = request.getParameter("name");
                    String email = request.getParameter("email");

                    // Lấy danh sách người dùng từ biến ngữ cảnh application (ServletContext)
                    java.util.Map<String, String> userInfo = (java.util.Map<String, String>)
                            application.getAttribute("userInfo");
                    if (userInfo == null) {
                        userInfo = new java.util.HashMap<>();
                    }

                    // Lưu thông tin người dùng vào Map với key là email (độc nhất)
                    userInfo.put(email, name);
                    // Sử dụng biến ngữ cảnh application để lưu trữ dữ liệu
                    application.setAttribute("userInfo", userInfo);
                }
            %>

            <%
                // Hiển thị thông tin người dùng từ biến ngữ cảnh application
                java.util.Map<String, String> userInfo = (java.util.Map<String, String>)
                        application.getAttribute("userInfo");
            %>
            <% if (userInfo != null && !userInfo.isEmpty()) { %>
            <div class="user-info">
                <h3>Thông tin người dùng đã lưu:</h3>
                <ul>
                    <% for (java.util.Map.Entry<String, String> entry : userInfo.entrySet()) { %>
                    <li>Tên: <%= entry.getValue() %>, Email: <%= entry.getKey() %></li>
                    <% } %>
                </ul>
            </div>
            <% } else { %>
            <p>Chưa có thông tin người dùng nào được lưu.</p>
            <% } %>
        </div>
    </body>
</html>