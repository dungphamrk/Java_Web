package org.example.ss2;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "RegisterServlet", value = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Set response content type and encoding
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        String name = request.getParameter("name");
        String email = request.getParameter("email");

        if (name != null && email != null && !name.isEmpty() && !email.isEmpty()) {
            HttpSession session = request.getSession();
            session.setAttribute("name", name);
            session.setAttribute("email", email);

            response.sendRedirect("thankyou.jsp");
        } else {
            // Handle error case
            response.getWriter().println("Lỗi: Vui lòng điền đầy đủ các trường");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Redirect GET requests to the form
        response.sendRedirect("registerByEmail.html");
    }

    public void destroy() {
    }
}