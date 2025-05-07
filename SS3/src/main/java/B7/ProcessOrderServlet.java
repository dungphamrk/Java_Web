package B7;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/ProcessOrderServlet")
public class ProcessOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy dữ liệu từ form
        String productName = request.getParameter("productName");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        double price = Double.parseDouble(request.getParameter("price"));

        // Lấy hoặc tạo mới OrderProcessor từ session
        HttpSession session = request.getSession();
        OrderProcessor orderProcessor = (OrderProcessor) session.getAttribute("orderProcessor");
        if (orderProcessor == null) {
            orderProcessor = new OrderProcessor();
            session.setAttribute("orderProcessor", orderProcessor);
        }

        // Thêm mục vào đơn hàng
        orderProcessor.addItem(productName, quantity, price);

        // Chuyển hướng về form để tiếp tục nhập
        response.sendRedirect("B7/orderForm.jsp");
    }
}