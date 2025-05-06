package org.example.ss2;import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "LifecycleServlet", value = "/LifecycleServlet")
public class LifecycleServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet Lifecycle</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; margin: 40px; }");
        out.println("h1 { color: #333; }");
        out.println(".phase { margin: 20px 0; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Vòng đời của Servlet</h1>");

        out.println("<div class='phase'>");
        out.println("<h2>1. Giai đoạn init</h2>");
        out.println("<p>Phương thức <code>init()</code> được gọi một lần duy nhất khi Servlet được tạo. ");
        out.println("Nó được sử dụng để khởi tạo các tài nguyên cần thiết cho Servlet.</p>");
        out.println("</div>");

        out.println("<div class='phase'>");
        out.println("<h2>2. Giai đoạn service</h2>");
        out.println("<p>Phương thức <code>service()</code> được gọi mỗi khi có yêu cầu từ client. ");
        out.println("Nó xử lý yêu cầu và trả về phản hồi. Trong trường hợp HTTP, các phương thức như ");
        out.println("<code>doGet()</code> hoặc <code>doPost()</code> sẽ được gọi tương ứng.</p>");
        out.println("</div>");

        out.println("<div class='phase'>");
        out.println("<h2>3. Giai đoạn destroy</h2>");
        out.println("<p>Phương thức <code>destroy()</code> được gọi một lần khi Servlet bị xóa khỏi bộ nhớ, ");
        out.println("thường là khi ứng dụng web bị tắt hoặc server dừng. ");
        out.println("Nó được sử dụng để giải phóng tài nguyên.</p>");
        out.println("</div>");

        out.println("</body>");
        out.println("</html>");
    }

    public void destroy() {
    }
}