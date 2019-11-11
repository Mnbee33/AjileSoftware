package shopping;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class AddItemTransaction {
    private Connection connection;

    public void addItem(String orderId, String sku, int qty) {
        try (Statement s = connection.createStatement()) {
            s.executeUpdate(String.format("insert into items values(%s, %s, %d)", orderId, sku, qty));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
