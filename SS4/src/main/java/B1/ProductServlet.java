package B1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "B1.ProductServlet", value = "/B1.ProductServlet")
public class ProductServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        List<Product> products=new ArrayList<Product>();
        products.add(new Product("Laptop Dell XPS 13", 1200.00, "Laptop cao cấp với màn hình 13 inch"));
        products.add(new Product("iPhone 14 Pro", 999.00, "Điện thoại thông minh với camera 48MP"));
        products.add(new Product("Tai nghe Sony WH-1000XM5", 349.99, "Tai nghe không dây chống ồn cao cấp"));
        products.add(new Product("Máy ảnh Canon EOS R5", 3899.00, "Máy ảnh không gương lật chuyên nghiệp"));

        request.setAttribute("products", products);
        request.getRequestDispatcher("B1.jsp").forward(request,response);
    }

    public void destroy() {
    }
}