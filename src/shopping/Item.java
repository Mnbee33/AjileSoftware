package shopping;

public class Item {
    private String orderId;
    private int quantity;
    private Product product;

    public Item(Product product, int qty) {
        this.product = product;
        this.quantity = qty;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
}
