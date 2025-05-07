package B6;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/addProduct")
public class AddProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        // Lấy dữ liệu từ form
        String id = request.getParameter("id");
        String productName = request.getParameter("productName");
        double price = Double.parseDouble(request.getParameter("price"));
        String description = request.getParameter("description");
        int stock = Integer.parseInt(request.getParameter("stock"));
        boolean status = Boolean.parseBoolean(request.getParameter("status"));

        // Tạo đối tượng Product
        Product product = new Product(id, productName, price, description, stock, status);

        // Lấy ServletContext
        ServletContext context = getServletContext();

        // Lấy danh sách sản phẩm từ context, nếu chưa có thì tạo mới
        List<Product> productList = (List<Product>) context.getAttribute("productList");
        if (productList == null) {
            productList = new ArrayList<>();
        }

        // Thêm sản phẩm vào danh sách
        productList.add(product);

        // Lưu danh sách sản phẩm vào context
        context.setAttribute("productList", productList);

        // Chuyển hướng đến trang hiển thị
        response.sendRedirect("displayProducts");
    }
}