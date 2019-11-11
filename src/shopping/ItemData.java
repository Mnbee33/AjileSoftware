package shopping;

public class ItemData {
    private int orderId;
    private int quantity;
    private String sku;

    public ItemData(int orderId, int quantity, String sku) {
        this.orderId = orderId;
        this.quantity = quantity;
        this.sku = sku;
    }

    public int getOrderId() {
        return orderId;
    }
}
