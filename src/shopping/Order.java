package shopping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private int orderId;
    private LocalDate date;
    private String status;
    private List<Item> items = new ArrayList<>();
    private String cusid;

    public Order(String cusid) {
        this.cusid = cusid;
    }

    public void addItem(Product p, int qty) {
        Item item = new Item(p, qty);
        items.add(item);
    }

    public int total() {
        int total = 0;
        for (Item item : items) {
            Product p = item.getProduct();
            int qty = item.getQuantity();
            total += p.getPrice() * qty;
        }
        return total;
    }
}
