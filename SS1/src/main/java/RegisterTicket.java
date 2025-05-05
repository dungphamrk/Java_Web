import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "RegisterTicket", value = "/RegisterTicket")
public class RegisterTicket extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        handleRegister(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        handleRegister(request, response);
    }
    protected void handleRegister(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy dữ liệu từ form
        String fullName = request.getParameter("fullName");
        String className = request.getParameter("className");
        String vehicleType = request.getParameter("vehicleType");
        String licensePlate = request.getParameter("licensePlate");

        // Kiểm tra dữ liệu cơ bản
        boolean isValid = fullName != null && !fullName.isEmpty()
                && className != null && !className.isEmpty()
                && vehicleType != null && !vehicleType.isEmpty()
                && licensePlate != null && !licensePlate.isEmpty();

        // Truyền thông tin sang JSP
        request.setAttribute("success", isValid);
        request.setAttribute("fullName", fullName);
        request.setAttribute("className", className);
        request.setAttribute("vehicleType", vehicleType);
        request.setAttribute("licensePlate", licensePlate);


        // Chuyển hướng tới trang kết quả
        RequestDispatcher dispatcher = request.getRequestDispatcher("resultRegister.jsp");
        dispatcher.forward(request, response);
    }

    public void destroy() {
    }
}