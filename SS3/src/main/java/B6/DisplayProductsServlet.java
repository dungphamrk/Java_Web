package B6;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/displayProducts")
public class DisplayProductsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        // Lấy ServletContext
        ServletContext context = getServletContext();

        // Lấy danh sách sản phẩm từ context
        List<Product> productList = (List<Product>) context.getAttribute("productList");

        // HTML để hiển thị
        out.println("<html><body>");
        out.println("<h2>Danh sách sản phẩm</h2>");
        out.println("<table border='1'>");
        out.println("<tr><th>ID</th><th>Tên sản phẩm</th><th>Giá</th><th>Mô tả</th><th>Số lượng</th><th>Trạng thái</th></tr>");

        if (productList != null && !productList.isEmpty()) {
            for (Product product : productList) {
                out.println("<tr>");
                out.println("<td>" + product.getId() + "</td>");
                out.println("<td>" + product.getProductName() + "</td>");
                out.println("<td>" + product.getPrice() + "</td>");
                out.println("<td>" + product.getDescription() + "</td>");
                out.println("<td>" + product.getStock() + "</td>");
                out.println("<td>" + (product.isStatus() ? "Còn hàng" : "Hết hàng") + "</td>");
                out.println("</tr>");
            }
        } else {
            out.println("<tr><td colspan='6'>Chưa có sản phẩm nào</td></tr>");
        }

        out.println("</table>");
        out.println("<br><a href='B6/addProduct.jsp'>Thêm sản phẩm mới</a>");
        out.println("</body></html>");
    }
}