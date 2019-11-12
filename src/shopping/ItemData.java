package shopping;

import java.util.Objects;

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

    public int getQuantity() {
        return quantity;
    }

    public String getSku() {
        return sku;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof ItemData) return false;
        ItemData itemData = (ItemData) o;
        return orderId == itemData.orderId &&
                quantity == itemData.quantity &&
                Objects.equals(sku, itemData.sku);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, quantity, sku);
    }
}
