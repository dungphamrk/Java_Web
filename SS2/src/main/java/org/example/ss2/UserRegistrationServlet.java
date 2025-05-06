package org.example.ss2;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "UserRegistrationServlet", value = "/UserRegistrationServlet")
public class UserRegistrationServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("register.html");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        // Get form parameters
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (name != null && email != null && password != null &&
                !name.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
            request.setAttribute("name", name);
            request.setAttribute("email", email);
            request.setAttribute("password", password);

            RequestDispatcher dispatcher = request.getRequestDispatcher("B5/userInfo.jsp");
            dispatcher.forward(request, response);
        } else {
            response.getWriter().println("Lỗi: Vui lòng điền đầy đủ các trường");
        }
    }

    public void destroy() {
    }
}