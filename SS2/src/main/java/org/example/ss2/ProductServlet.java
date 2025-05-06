package org.example.ss2;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "ProductServlet", value = "/ProductServlet")
public class ProductServlet extends HttpServlet {
    private static List<Product> products = new ArrayList<>();
    private static int nextId = 1;

    @Override
    public void init() throws ServletException {
        // Initialize with some sample products
        products.add(new Product(nextId++, "Laptop", 999.99));
        products.add(new Product(nextId++, "Smartphone", 499.99));
        products.add(new Product(nextId++, "Headphones", 79.99));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String idStr = request.getParameter("id");

        if ("edit".equals(action) && idStr != null) {
            try {
                int id = Integer.parseInt(idStr);
                Product product = products.stream()
                        .filter(p -> p.getId() == id)
                        .findFirst()
                        .orElse(null);
                if (product != null) {
                    request.setAttribute("editProduct", product);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("B6/updateProduct.jsp");
                    dispatcher.forward(request, response);
                    return;
                } else {
                    request.setAttribute("error", "Không tìm thấy sản phẩm");
                }
            } catch (NumberFormatException e) {
                request.setAttribute("error", "ID sản phẩm không hợp lệ");
            }
        }

        // Set products attribute for JSP
        request.setAttribute("products", products);
        String success = request.getParameter("success");
        if ("delete".equals(success)) {
            request.setAttribute("success", "Xóa sản phẩm thành công");
        } else if ("true".equals(success)) {
            request.setAttribute("success", "Cập nhật sản phẩm thành công");
        }

        // Forward to productList.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("B6/productList.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set request encoding
        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");

        if ("update".equals(action)) {
            String idStr = request.getParameter("id");
            String name = request.getParameter("name");
            String priceStr = request.getParameter("price");

            // Validation
            if (idStr == null || name == null || priceStr == null ||
                    name.trim().isEmpty() || priceStr.trim().isEmpty()) {
                request.setAttribute("error", "Vui lòng điền đầy đủ thông tin");
                try {
                    int id = Integer.parseInt(idStr);
                    Product product = products.stream()
                            .filter(p -> p.getId() == id)
                            .findFirst()
                            .orElse(null);
                    request.setAttribute("editProduct", product);
                } catch (NumberFormatException e) {
                    request.setAttribute("error", "ID sản phẩm không hợp lệ");
                }
                RequestDispatcher dispatcher = request.getRequestDispatcher("B6/updateProduct.jsp");
                dispatcher.forward(request, response);
                return;
            }

            try {
                int id = Integer.parseInt(idStr);
                double price = Double.parseDouble(priceStr);
                if (price < 0) {
                    request.setAttribute("error", "Giá sản phẩm phải lớn hơn hoặc bằng 0");
                    Product product = products.stream()
                            .filter(p -> p.getId() == id)
                            .findFirst()
                            .orElse(null);
                    request.setAttribute("editProduct", product);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("B6/updateProduct.jsp");
                    dispatcher.forward(request, response);
                    return;
                }

                Product product = products.stream()
                        .filter(p -> p.getId() == id)
                        .findFirst()
                        .orElse(null);
                if (product != null) {
                    product.setName(name);
                    product.setPrice(price);
                    response.sendRedirect("ProductServlet?success=true");
                } else {
                    request.setAttribute("error", "Không tìm thấy sản phẩm");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("B6/updateProduct.jsp");
                    dispatcher.forward(request, response);
                }
            } catch (NumberFormatException e) {
                request.setAttribute("error", "Giá sản phẩm không hợp lệ");
                try {
                    int id = Integer.parseInt(idStr);
                    Product product = products.stream()
                            .filter(p -> p.getId() == id)
                            .findFirst()
                            .orElse(null);
                    request.setAttribute("editProduct", product);
                } catch (NumberFormatException ex) {
                    request.setAttribute("error", "ID sản phẩm không hợp lệ");
                }
                RequestDispatcher dispatcher = request.getRequestDispatcher("B6/updateProduct.jsp");
                dispatcher.forward(request, response);
            }
        } else if ("delete".equals(action)) {
            String idStr = request.getParameter("id");
            try {
                int id = Integer.parseInt(idStr);
                boolean removed = products.removeIf(p -> p.getId() == id);
                if (removed) {
                    response.sendRedirect("ProductServlet?success=delete");
                } else {
                    request.setAttribute("error", "Không tìm thấy sản phẩm để xóa");
                    request.setAttribute("products", products);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("B6/productList.jsp");
                    dispatcher.forward(request, response);
                }
            } catch (NumberFormatException e) {
                request.setAttribute("error", "ID sản phẩm không hợp lệ");
                request.setAttribute("products", products);
                RequestDispatcher dispatcher = request.getRequestDispatcher("B6/productList.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            // Add new product
            String name = request.getParameter("name");
            String priceStr = request.getParameter("price");

            if (name != null && !name.isEmpty() && priceStr != null && !priceStr.isEmpty()) {
                try {
                    double price = Double.parseDouble(priceStr);
                    if (price >= 0) {
                        products.add(new Product(nextId++, name, price));
                    } else {
                        request.setAttribute("error", "Giá sản phẩm phải lớn hơn hoặc bằng 0");
                    }
                } catch (NumberFormatException e) {
                    request.setAttribute("error", "Giá sản phẩm không hợp lệ");
                }
            } else {
                request.setAttribute("error", "Vui lòng điền đầy đủ thông tin");
            }
            response.sendRedirect("ProductServlet");
        }
    }

    public void destroy() {
    }
}