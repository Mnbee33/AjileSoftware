package shopping;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestProxy {
    @BeforeEach
    void setUp() {
        DB.init();
        DB.clear();
        DB.store(new ProductData("ProxyTestName1", 456, "ProxyTest1"));
    }

    @AfterEach
    void tearDown() {
        DB.close();
    }

    @Test
    void testProductProxy() throws Exception {
        Product p = new ProductProxy("ProxyTest1");
        assertEquals(456, p.getPrice());
        assertEquals("ProxyTestName1", p.getName());
        assertEquals("ProxyTest1", p.getSku());
    }

    @Test
    void testOrderProxyCustomerId() {
        OrderData od = DB.newOrder("testOrderProxyCustomerId");
        Order op = new OrderProxy(od.getOrderId());
        assertEquals(od.getCustomerId(), op.getCustomerId());
    }

    @Test
    void testOrderProxyTotal() {
        DB.store(new ProductData("Wheaties", 349, "wheaties"));
        DB.store(new ProductData("Crest", 258, "crest"));

        ProductProxy wheaties = new ProductProxy("wheaties");
        ProductProxy crest = new ProductProxy("crest");

        OrderData od = DB.newOrder("testOrderProxy");
        OrderProxy order = new OrderProxy(od.getOrderId());

        order.addItem(crest, 1);
        order.addItem(wheaties, 2);
        assertEquals(956, order.total());
    }
}
