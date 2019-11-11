package shopping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DB {
    private static Map<String, ProductData> productTable;
    private static List<ItemData> itemTable;
    private static Map<Integer, OrderData> orderTable;

    private static int lastOrderId;

    public static void init() {
        productTable = new HashMap<>();
        itemTable = new ArrayList<>();
        orderTable = new HashMap<>();
        lastOrderId = 0;
    }

    public static void clear() {
        productTable.clear();
        itemTable.clear();
        orderTable.clear();
        lastOrderId = 0;
    }

    public static void close() {
    }

    public static void store(ProductData pd) {
        productTable.put(pd.getSku(), pd);
    }

    public static ProductData getProductData(String sku) {
        return productTable.get(sku);
    }

    public static void store(ItemData id) {
        itemTable.add(id);
    }

    public static ItemData[] getItemsForOrder(int orderId) {
        return itemTable.stream()
                .filter(id -> id.getOrderId() == orderId)
                .toArray(ItemData[]::new);
    }

    public static OrderData newOrder(String cusid) {
        OrderData o = new OrderData(cusid, ++lastOrderId);
        orderTable.put(lastOrderId, o);
        return o;
    }

    public static OrderData getOrderData(int orderId) {
        return orderTable.get(orderId);
    }
}
