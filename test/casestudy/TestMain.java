package casestudy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestMain {
    @Test
    void testTextParserTransactionSource() {
        TransactionSource ts = new TextParserTransactionSource("src/casestudy/src/transaction.txt");
        Transaction t = ts.getTransaction();
        assertNotNull(t);
        assertTrue(t instanceof AddHourlyEmployee);

        t.execute();
        Employee e = PayrollDatabase.getEmployee(1);
        assertNotNull(e);
        assertEquals("Bob", e.getName());
        assertEquals("Home", e.getAddress());
    }
}
