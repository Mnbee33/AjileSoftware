package shopping;

public interface Order {
    String getCustomerId();

    void addItem(Product p, int quantity);

    int total();
}
