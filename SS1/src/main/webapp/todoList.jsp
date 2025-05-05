<%--
  Created by IntelliJ IDEA.
  User: dungp
  Date: 5/5/2025
  Time: 11:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.ToDoListServlet.Task" %>
<html>
  <head>
    <title>To-Do List</title>
    <style>
      table { border-collapse: collapse; width: 80%; margin: 20px auto; }
      th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
      th { background-color: #f2f2f2; }
      .form-container { margin: 20px auto; width: 80%; text-align: center; }
    </style>
  </head>
  <body>
    <h2>Tạo công việc To-Do List</h2>
    <div class="form-container">
      <form action="com.example.ToDoListServlet" method="post">
        <input type="text" name="jobName" placeholder="Tên công việc" required>
        <input type="submit" value="Add">
      </form>
    </div>

    <h2>Danh sách công việc</h2>
    <table>
      <tr>
        <th>Tên công việc</th>
        <th>Trạng thái</th>
        <th>Hành động</th>
      </tr>
      <%
        List<Task> taskList = (List<Task>) request.getAttribute("taskList");
        if (taskList != null) {
          for (Task task : taskList) {
      %>
      <tr>
        <td><%= task.getJobName() %></td>
        <td><%= task.getStatus() %></td>
        <td>
          <form action="com.example.ToDoListServlet" method="post" style="display:inline;">
            <input type="hidden" name="jobName" value="<%= task.getJobName() %>">
            <select name="status" onchange="this.form.submit()">
              <option value="Ongoing" <%= "Ongoing".equals(task.getStatus()) ? "selected" : "" %>>Ongoing</option>
              <option value="Completed" <%= "Completed".equals(task.getStatus()) ? "selected" : "" %>>Completed</option>
            </select>
          </form>
        </td>
      </tr>
      <%
        }
      } else {
      %>
      <tr><td colspan="3">Không có công việc.</td></tr>
      <%
        }
      %>
    </table>
  </body>
</html>