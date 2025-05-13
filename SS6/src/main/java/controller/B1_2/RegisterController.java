package controller.B1_2;

import model.B1_2.User;
import service.B1_2.UsersService;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/register")
public class RegisterController extends HttpServlet {
    private final UsersService svc = new UsersService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("views/B1_2/user/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String u     = req.getParameter("username");
        String p     = req.getParameter("password");
        String e     = req.getParameter("email");
        String phone = req.getParameter("phone");

        try {
            if (svc.authenticate(u, p) != null) {
                req.setAttribute("error", "Username đã tồn tại");
                req.getRequestDispatcher("views/B1_2/user/register.jsp").forward(req, resp);
                return;
            }
            svc.register(new User(u,p,e,phone));
            resp.sendRedirect("login");
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
}
