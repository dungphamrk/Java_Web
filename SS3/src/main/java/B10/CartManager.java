package B10;

import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private List<Product> cart;

    public CartManager() {
        this.cart = new ArrayList<>();
    }

    public boolean addProduct(Product product) {
        // Kiểm tra nếu sản phẩm đã tồn tại trong giỏ hàng dựa trên id
        for (Product p : cart) {
            if (p.getId() == product.getId()) {
                return false; // Sản phẩm đã tồn tại
            }
        }
        cart.add(product);
        return true; // Thêm thành công
    }

    public List<Product> getCart() {
        return cart;
    }

    public void removeProduct(int id) {
        cart.removeIf(product -> product.getId() == id);
    }

    public double getTotalPrice() {
        double total = 0;
        for (Product product : cart) {
            total += product.getPrice();
        }
        return total;
    }
}