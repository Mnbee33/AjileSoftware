package shopping;

public class OrderProxy implements Order {
    private int orderId;

    public OrderProxy(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public String getCustomerId() {
        OrderData od = DB.getOrderData(orderId);
        return od.getCustomerId();
    }

    @Override
    public void addItem(Product p, int quantity) {
        try {
            ItemData id = new ItemData(orderId, quantity, p.getSku());
            DB.store(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int total() {
        OrderImp imp = new OrderImp(getCustomerId());
        ItemData[] itemDataArrays = DB.getItemsForOrder(orderId);
        for (ItemData id : itemDataArrays) {
            imp.addItem(new ProductProxy(id.getSku()), id.getQuantity());
        }
        return imp.total();
    }
}
