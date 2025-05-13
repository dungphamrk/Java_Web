package controller.B3;


import model.B3.Product;
import service.B3.ProductCartService;
import service.B3.ProductCartServiceImp;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "product", value = "/product")
public class ProductController extends HttpServlet {
    private final ProductCartService productCartService;

    public ProductController() {
        productCartService= new ProductCartServiceImp();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> productList = productCartService.getAllProducts();
        req.setAttribute("productList", productList);
        req.getRequestDispatcher("views/B3/productList.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int productId = Integer.parseInt(req.getParameter("productId"));
        productCartService.addProductCart(productId,1);
        doGet(req, resp);
    }
}
