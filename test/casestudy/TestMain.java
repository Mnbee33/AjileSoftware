package casestudy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TestMain {
    @BeforeEach
    void setUp() {
        PayrollDatabase.clear();
    }

    @Test
    void testTextParserTransactionSource_AddEmp() {
        PayrollApplication application = new PayrollApplication();
        TransactionSource addEmp = new TextParserTransactionSource("test/casestudy/src/AddEmp.txt");
        application.setSource(addEmp);
        application.execute();

        Employee e1 = PayrollDatabase.getEmployee(1);
        assertNotNull(e1);
        assertEquals("Bob", e1.getName());
        assertTrue(e1.getClassification() instanceof HourlyClassification);

        Employee e2 = PayrollDatabase.getEmployee(2);
        assertNotNull(e2);
        assertEquals("Bill", e2.getName());
        assertTrue(e2.getClassification() instanceof SalariedClassification);

        Employee e3 = PayrollDatabase.getEmployee(3);
        assertNotNull(e3);
        assertEquals("Alice", e3.getName());
        assertTrue(e3.getClassification() instanceof CommissionedClassification);
    }

    @Test
    void testTextParserTransactionSource_DelEmp() {
        PayrollApplication application = new PayrollApplication();
        TransactionSource addEmp = new TextParserTransactionSource("test/casestudy/src/AddEmp.txt");
        application.setSource(addEmp);
        application.execute();
        Employee e1 = PayrollDatabase.getEmployee(1);
        assertNotNull(e1);

        TransactionSource delEmp = new TextParserTransactionSource("test/casestudy/src/DelEmp.txt");
        application.setSource(delEmp);
        application.execute();
        e1 = PayrollDatabase.getEmployee(1);
        assertNull(e1);
    }

    @Test
    void testTextParserTransactionSource_TimeCard() {
        PayrollApplication application = new PayrollApplication();
        TransactionSource addEmp = new TextParserTransactionSource("test/casestudy/src/AddEmp.txt");
        application.setSource(addEmp);
        application.execute();

        TransactionSource timeCard = new TextParserTransactionSource("test/casestudy/src/TimeCard.txt");
        application.setSource(timeCard);
        application.execute();

        Employee e1 = PayrollDatabase.getEmployee(1);
        assertNotNull(e1);

        HourlyClassification hc = (HourlyClassification) e1.getClassification();
        TimeCard tc = hc.getTimeCard(LocalDate.of(2001, 10, 31));
        assertEquals(8.0, tc.getHours());
    }
}
