package B5;

public class Product {
    private static int countId=0;
    private int id;
    private String name;
    private double price;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Product() {
    }

    public Product( String name,double price, String description) {
        this.id = ++countId;
        this.name = name;
        this.price = price;

        this.description = description;
    }
}
