package shopping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderImp implements Order {
    private int orderId;
    private LocalDate date;
    private String status;
    private List<Item> items = new ArrayList<>();
    private String cusid;

    public OrderImp(String cusid) {
        this.cusid = cusid;
    }

    @Override
    public String getCustomerId() {
        return cusid;
    }

    @Override
    public void addItem(Product p, int quantity) {
        Item item = new Item(p, quantity);
        items.add(item);
    }

    @Override
    public int total() {
        try {
            int total = 0;
            for (Item item : items) {
                Product p = item.getProduct();
                int qty = item.getQuantity();
                total += p.getPrice() * qty;
            }
            return total;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
