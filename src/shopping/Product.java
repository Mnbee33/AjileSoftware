package shopping;

public class Product {
    private String name;
    private int price;
    private String sku;

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
