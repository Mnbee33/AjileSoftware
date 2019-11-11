package shopping;

public class ProductData {
    private String name;
    private int price;
    private String sku;

    public ProductData(String name, int price, String sku) {
        this.name = name;
        this.price = price;
        this.sku = sku;
    }

    public String getSku() {
        return sku;
    }
}
