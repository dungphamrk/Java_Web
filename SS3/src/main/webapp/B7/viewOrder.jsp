<%-- viewOrder.jsp --%>
<%-- Đặt trong thư mục src/main/webapp --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="B7.OrderProcessor" %>
<%@ page import="B7.OrderProcessor.OrderItem" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Xem đơn hàng</title>
        <meta charset="UTF-8">
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
        <style>
            body {
                font-family: 'Roboto', Arial, sans-serif;
                line-height: 1.6;
                margin: 20px;
            }
            .order-container {
                max-width: 800px;
                margin: 0 auto;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
            }
            th, td {
                border: 1px solid #ddd;
                padding: 8px;
                text-align: left;
            }
            th {
                background-color: #f2f2f2;
            }
        </style>
    </head>
    <body>
        <div class="order-container">
            <h2>Chi tiết đơn hàng</h2>
            <%
                OrderProcessor orderProcessor = (OrderProcessor) session.getAttribute("orderProcessor");
                if (orderProcessor != null && !orderProcessor.getItems().isEmpty()) {
                    List<OrderItem> items = orderProcessor.getItems();
            %>
            <table>
                <tr>
                    <th>Sản phẩm</th>
                    <th>Số lượng</th>
                    <th>Giá mỗi sản phẩm</th>
                    <th>Tổng cộng</th>
                </tr>
                <%
                    for (OrderItem item : items) {
                %>
                <tr>
                    <td><%= item.getProductName() %></td>
                    <td><%= item.getQuantity() %></td>
                    <td><%= String.format("%.2f", item.getPrice()) %></td>
                    <td><%= String.format("%.2f", item.getItemTotal()) %></td>
                </tr>
                <%
                    }
                %>
                <tr>
                    <td colspan="3" style="text-align: right;"><strong>Tổng giá trị đơn hàng:</strong></td>
                    <td><strong><%= String.format("%.2f", orderProcessor.calculateTotal()) %></strong></td>
                </tr>
            </table>
            <%
            } else {
            %>
            <p>Chưa có sản phẩm nào trong đơn hàng.</p>
            <%
                }
            %>
            <br>
            <a href="orderForm.jsp">Quay lại nhập đơn hàng</a>
        </div>
    </body>
</html>