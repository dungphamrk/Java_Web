package com.data.ss14.model.B8;

public class Order {
    private String username;
    private String product;
    private int quantity;

    public Order() {}
    public Order(String username, String product, int quantity) {
        this.username = username;
        this.product = product;
        this.quantity = quantity;
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getProduct() { return product; }
    public void setProduct(String product) { this.product = product; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}
