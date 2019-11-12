package shopping;

public class ProductImp implements Product {
    private String name;
    private int price;
    private String sku;

    public ProductImp(String sku, String name, int price) {
        this.sku = sku;
        this.name = name;
        this.price = price;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSku() {
        return sku;
    }
}
