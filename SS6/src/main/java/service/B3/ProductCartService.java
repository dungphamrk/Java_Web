package service.B3;

import model.B3.Product;
import model.B3.ProductCart;

import java.util.List;

public interface ProductCartService {
    List<Product> getAllProducts();
    boolean addProductCart(int productId, int quantity);
    boolean removeProductCart(int productCartId);
    List<ProductCart> getProductCart();
    Product getProductById(int productId);
}