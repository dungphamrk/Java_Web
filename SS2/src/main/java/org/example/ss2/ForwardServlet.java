package org.example.ss2;

import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "ForwardServlet", value = "/ForwardServlet")
public class ForwardServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String name = "Phạm Quang Dũng";
        int age = 21;
        request.setAttribute("userName", name);
        request.setAttribute("userAge", age);
        request.getRequestDispatcher("userInfor.jsp").forward(request, response);
    }

    public void destroy() {
    }
}