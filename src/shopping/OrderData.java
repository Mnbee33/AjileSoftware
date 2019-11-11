package shopping;

public class OrderData {
    private String cusid;
    private int orderId;

    public OrderData(String cusid, int orderId) {
        this.cusid = cusid;
        this.orderId = orderId;
    }

    public int getOrderId() {
        return orderId;
    }
}
