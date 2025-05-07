package B8;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/BookServlet")
public class BookServlet extends HttpServlet {
    private List<Book> bookList;

    @Override
    public void init() throws ServletException {
        // Khởi tạo danh sách sách khi Servlet được tạo
        bookList = new ArrayList<>();
        getServletContext().setAttribute("bookList", bookList);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("search".equals(action)) {
            String searchTitle = request.getParameter("searchTitle");
            List<Book> filteredBooks = bookList.stream()
                    .filter(book -> book.getTitle().toLowerCase().contains(searchTitle.toLowerCase()))
                    .collect(Collectors.toList());
            request.setAttribute("filteredBooks", filteredBooks);
        }
        // Chuyển hướng đến home.jsp
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            // Lấy dữ liệu từ form
            String title = request.getParameter("title");
            String author = request.getParameter("author");
            int year = Integer.parseInt(request.getParameter("year"));

            // Tạo đối tượng Book và thêm vào danh sách
            Book book = new Book(title, author, year);
            bookList.add(book);

            // Cập nhật danh sách trong ServletContext
            getServletContext().setAttribute("bookList", bookList);
        }

        // Chuyển hướng lại home.jsp
        response.sendRedirect("BookServlet");
    }
}