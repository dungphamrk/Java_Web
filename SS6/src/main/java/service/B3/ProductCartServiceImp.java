package service.B3;

import dao.B3.ProductCartDaoImp;
import dao.B3.ProductCartDao;
import model.B3.Product;
import model.B3.ProductCart;

import java.util.List;

public class ProductCartServiceImp implements ProductCartService {
    private final ProductCartDao productCartDao;

    public ProductCartServiceImp() {
        productCartDao= new ProductCartDaoImp();
    }

    @Override
    public List<Product> getAllProducts() {
        return productCartDao.getAllProducts();
    }

    @Override
    public boolean addProductCart(int productId, int quantity) {
        return productCartDao.addProductCart(productId, quantity);
    }

    @Override
    public boolean removeProductCart(int productCartId) {
        return productCartDao.removeProductCart(productCartId);
    }


    @Override
    public List<ProductCart> getProductCart() {
        return productCartDao.getProductCart();
    }

    @Override
    public Product getProductById(int productId) {
        return productCartDao.getProductById(productId);
    }
}