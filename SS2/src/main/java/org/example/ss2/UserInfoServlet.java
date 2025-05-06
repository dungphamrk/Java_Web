package org.example.ss2;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "UserInfoServlet", value = "/UserInfoServlet")
public class UserInfoServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String name = request.getParameter("name");
        String age = request.getParameter("age");

        request.setAttribute("name", name);
        request.setAttribute("age", age);

        System.out.println(name);
        System.out.println(age);

        request.getRequestDispatcher("/userInfor.jsp").forward(request, response);
    }

    public void destroy() {
    }
}