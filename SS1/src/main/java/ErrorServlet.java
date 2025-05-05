import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "ErrorServlet", value = "/ErrorServlet")
public class ErrorServlet extends HttpServlet {
    private String message;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        try {
            // Lỗi giả lập: chia cho 0
            int result = 10 / 0;

            response.getWriter().println("Kết quả là: " + result);
        } catch (Exception e) {
            e.printStackTrace();

            request.setAttribute("errorMessage", "Đã xảy ra lỗi trong quá trình xử lý. Vui lòng thử lại sau.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);
        }
    }

    public void destroy() {
    }
}