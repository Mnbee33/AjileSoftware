package casestudy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TestMain {
    private PayrollApplication application;

    @BeforeEach
    void setUp() {
        PayrollDatabase.clear();
        application = new PayrollApplication();
    }

    @Test
    void testTextParserTransactionSource_AddEmp() {
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
        TransactionSource addEmp = new TextParserTransactionSource("test/casestudy/src/AddEmp.txt");
        application.setSource(addEmp);
        application.execute();
        Employee e = PayrollDatabase.getEmployee(1);
        assertNotNull(e);

        TransactionSource delEmp = new TextParserTransactionSource("test/casestudy/src/DelEmp.txt");
        application.setSource(delEmp);
        application.execute();
        e = PayrollDatabase.getEmployee(1);
        assertNull(e);
    }

    @Test
    void testTextParserTransactionSource_TimeCard() {
        TransactionSource addEmp = new TextParserTransactionSource("test/casestudy/src/AddEmp.txt");
        application.setSource(addEmp);
        application.execute();

        TransactionSource timeCard = new TextParserTransactionSource("test/casestudy/src/TimeCard.txt");
        application.setSource(timeCard);
        application.execute();

        Employee e = PayrollDatabase.getEmployee(1);
        assertNotNull(e);

        HourlyClassification hc = (HourlyClassification) e.getClassification();
        TimeCard tc = hc.getTimeCard(LocalDate.of(2001, 10, 31));
        assertEquals(8.0, tc.getHours());
    }

    @Test
    void testTextParserTransactionSource_SalesReceipt() {
        TransactionSource addEmp = new TextParserTransactionSource("test/casestudy/src/AddEmp.txt");
        application.setSource(addEmp);
        application.execute();

        TransactionSource saleReceipt = new TextParserTransactionSource("test/casestudy/src/SalesReceipt.txt");
        application.setSource(saleReceipt);
        application.execute();

        Employee e = PayrollDatabase.getEmployee(3);
        assertNotNull(e);

        CommissionedClassification cc = (CommissionedClassification) e.getClassification();
        SalesReceipt sr = cc.getSalesReceipt(LocalDate.of(2001, 10, 31));
        assertEquals(8.0, sr.getAmount());
    }

    @Test
    void testTextParserTransactionSource_ServiceCharge() {
        TransactionSource addEmp = new TextParserTransactionSource("test/casestudy/src/AddEmp.txt");
        application.setSource(addEmp);
        application.execute();

        Employee e = PayrollDatabase.getEmployee(1);
        assertNotNull(e);

        int memberId = 86;
        e.setAffiliation(new UnionAffiliation(memberId, 8.0));
        PayrollDatabase.addUnionMember(memberId, e);

        TransactionSource serviceCharge = new TextParserTransactionSource("test/casestudy/src/ServiceCharge.txt");
        application.setSource(serviceCharge);
        application.execute();

        UnionAffiliation uf = (UnionAffiliation) e.getAffiliation();
        double sc = uf.getServiceCharge(LocalDate.of(2001, 10, 31));
        assertEquals(12.95, sc, .001);
    }

    @Test
    void testTextParserTransactionSource_ChgEmp() {
        TransactionSource addEmp = new TextParserTransactionSource("test/casestudy/src/AddEmp.txt");
        application.setSource(addEmp);
        application.execute();

        TransactionSource serviceCharge = new TextParserTransactionSource("test/casestudy/src/ChgEmp.txt");
        application.setSource(serviceCharge);
        application.execute();

        Employee e1 = PayrollDatabase.getEmployee(1);
        assertEquals("Robert", e1.getName());
        assertEquals("nextHome", e1.getAddress());
        CommissionedClassification cc = (CommissionedClassification) e1.getClassification();
        assertEquals(2200.00, cc.getSalary());
        assertTrue(e1.getMethod() instanceof HoldMethod);
        assertEquals(7.5, cc.getRate());
        assertEquals(e1, PayrollDatabase.getUnionMember(86));

        Employee e2 = PayrollDatabase.getEmployee(2);
        HourlyClassification hc = (HourlyClassification) e2.getClassification();
        assertEquals(12.25, hc.getRate());
        assertTrue(e2.getMethod() instanceof DirectMethod);
        assertNull(PayrollDatabase.getUnionMember(87));

        Employee e3 = PayrollDatabase.getEmployee(3);
        SalariedClassification sc = (SalariedClassification) e3.getClassification();
        assertEquals(2000.00, sc.getSalary());
        assertTrue(e3.getMethod() instanceof MailMethod);
    }
}
