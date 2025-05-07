package B9;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
    private UserManager userManager;

    @Override
    public void init() throws ServletException {
        userManager = new UserManager();
        getServletContext().setAttribute("userManager", userManager);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            userManager.removeUser(id);
        }
        response.sendRedirect("B9/listUser.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("add".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String email = request.getParameter("email");

            User user = new User(id, name, email);
            userManager.addUser(user);

            request.setAttribute("message", "Đăng ký thành công!");
            response.sendRedirect("B9/listUser.jsp");
        }
    }
}