package shopping;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String cusid;
    private String name;
    private String address;
    private List<Order> orders = new ArrayList<>();
}
