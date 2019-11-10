package factory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPayroll implements DatabaseFactory, Database {
    private String payRollQuery;

    @Test
    void testExecute() {
        Payroll p = new Payroll();
        p.setFactory(this);

        p.execute();
        assertEquals("From Payroll", payRollQuery);
    }

    @Override
    public Database createDatabase() {
        return this;
    }

    @Override
    public void update(String query) {
        System.out.println("call TestPayroll");
        payRollQuery = query;
    }
}
