package B5;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/productList")
public class ProductServlet extends HttpServlet {
    private List<Product> products;

    @Override
    public void init() throws ServletException {
        // Khởi tạo danh sách sản phẩm tĩnh
        products = new ArrayList<>();
        products.add(new Product("Laptop Dell XPS 13", 1200.00, "Laptop cao cấp với màn hình 13 inch"));
        products.add(new Product("iPhone 14 Pro", 999.00, "Điện thoại thông minh với camera 48MP"));
        products.add(new Product("Tai nghe Sony WH-1000XM5", 349.99, "Tai nghe không dây chống ồn cao cấp"));
        products.add(new Product("Máy ảnh Canon EOS R5", 3899.00, "Máy ảnh không gương lật chuyên nghiệp"));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        // Lấy tham số ID từ form tìm kiếm
        int searchId = Integer.parseInt(request.getParameter("id"));
        Product foundProduct = null;

        // Tìm sản phẩm theo ID nếu có
        if (searchId != 0 ) {
            for (Product product : products) {
                if (product.getId()==(searchId)) {
                    foundProduct = product;
                    break;
                }
            }
        }

        // Đưa danh sách sản phẩm và sản phẩm tìm thấy (nếu có) vào request
        request.setAttribute("products", products);
        request.setAttribute("foundProduct", foundProduct);
        request.setAttribute("searchId", searchId);

        // Chuyển hướng đến JSP
        request.getRequestDispatcher("B4/productList.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response); // Xử lý POST giống GET
    }

    @Override
    public void destroy() {
    }
}