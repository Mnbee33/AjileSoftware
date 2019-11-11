package shopping;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TestDB {
    @BeforeEach
    void setUp() {
        DB.init();
        DB.clear();
    }

    @AfterEach
    void tearDown() {
        DB.close();
    }

    @Test
    void testStoreProduct() {
        ProductData storedProduct = new ProductData("MyProduct", 1234, "999");
        DB.store(storedProduct);
        ProductData retrievedProduct = DB.getProductData("999");
        assertEquals(storedProduct, retrievedProduct);
    }

    @Test
    void testNoProducts() {
        ProductData none = DB.getProductData("none");
        assertNull(none);
    }

    @Test
    void testStoreItem() {
        ItemData storedItem = new ItemData(1, 3, "sku");
        DB.store(storedItem);
        ItemData[] retrievedItems = DB.getItemsForOrder(1);
        assertEquals(1, retrievedItems.length);
        assertEquals(storedItem, retrievedItems[0]);
    }

    @Test
    void testNoItems() {
        ItemData[] none = DB.getItemsForOrder(42);
        assertEquals(0, none.length);
    }

    @Test
    void testOrderKeyGenerations() {
        OrderData o1 = DB.newOrder("Bob");
        OrderData o2 = DB.newOrder("Bill");
        int firstOrderId = o1.getOrderId();
        int secondOrderId = o2.getOrderId();
        assertEquals(firstOrderId + 1, secondOrderId);
    }

    @Test
    void testNoOrder() {
        OrderData none = DB.getOrderData(0);
        assertNull(none);
    }
}
