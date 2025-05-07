package B7;

import java.util.ArrayList;
import java.util.List;

public class OrderProcessor {
    public static class OrderItem {
        private String productName;
        private int quantity;
        private double price;

        public OrderItem(String productName, int quantity, double price) {
            this.productName = productName;
            this.quantity = quantity;
            this.price = price;
        }

        public String getProductName() {
            return productName;
        }

        public int getQuantity() {
            return quantity;
        }

        public double getPrice() {
            return price;
        }

        public double getItemTotal() {
            return quantity * price;
        }
    }

    private List<OrderItem> items;

    public OrderProcessor() {
        this.items = new ArrayList<>();
    }

    // Thêm một mục vào danh sách đơn hàng
    public void addItem(String productName, int quantity, double price) {
        items.add(new OrderItem(productName, quantity, price));
    }

    // Tính tổng giá trị đơn hàng
    public double calculateTotal() {
        double total = 0;
        for (OrderItem item : items) {
            total += item.getItemTotal();
        }
        return total;
    }

    // Lấy danh sách các mục trong đơn hàng
    public List<OrderItem> getItems() {
        return items;
    }
}