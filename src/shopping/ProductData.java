package shopping;

import java.util.Objects;

public class ProductData implements Product {
    private String name;
    private int price;
    private String sku;


    public ProductData(String name, int price, String sku) {
        this.name = name;
        this.price = price;
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getSku() {
        return sku;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductData)) return false;
        ProductData that = (ProductData) o;
        return price == that.price &&
                Objects.equals(name, that.name) &&
                Objects.equals(sku, that.sku);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, sku);
    }
}
