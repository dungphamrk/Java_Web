package B8;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/revenueList")
public class RevenueServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Tạo danh sách doanh thu tĩnh
        List<Revenue> revenues = new ArrayList<>();
        revenues.add(new Revenue("Tháng 1", 2000.0));
        revenues.add(new Revenue("Tháng 2", 1500.0));
        revenues.add(new Revenue("Tháng 3", 3000.0));
        revenues.add(new Revenue("Tháng 4", 2500.0));
        revenues.add(new Revenue("Tháng 5", 3500.0));
        revenues.add(new Revenue("Tháng 6", 1800.0));

        // Đưa danh sách vào request
        request.setAttribute("revenues", revenues);

        // Chuyển hướng đến JSP
        request.getRequestDispatcher("B8/revenueList.jsp").forward(request, response);
    }
}