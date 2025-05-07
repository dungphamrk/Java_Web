package B6;

public class Product {
    private String id;
    private String productName;
    private double price;
    private String description;
    private int stock;
    private boolean status;

    // Constructor
    public Product(String id, String productName, double price, String description, int stock, boolean status) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.stock = stock;
        this.status = status;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
    public boolean isStatus() { return status; }
    public void setStatus(boolean status) { this.status = status; }

    @Override
    public String toString() {
        return "Product{id='" + id + "', productName='" + productName + "', price=" + price +
                ", description='" + description + "', stock=" + stock + ", status=" + status + "}";
    }
}