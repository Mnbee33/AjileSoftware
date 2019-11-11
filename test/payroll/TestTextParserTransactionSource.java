package payroll;

import payroll.PayApplication.PayrollApplication;
import payroll.PayrollDatabase.GlobalDatabase;
import payroll.PayrollDatabaseImplementation.PayrollDatabaseImplementation;
import payroll.PayrollDomain.Employee;
import payroll.PayrollFactory.PayrollFactory;
import payroll.PayrollFactoryImplementation.*;
import payroll.SourceFactoryImplementation.TextParserTransactionSource;
import payroll.TransactionApplication.Transaction;
import payroll.TransactionApplication.TransactionSource;
import payroll.TransactionFactory.TransactionFactory;
import payroll.TransactionFactoryImplementation.PaydayTransaction;
import payroll.TransactionFactoryImplementation.TransactionFactoryImplementation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestTextParserTransactionSource {
    private PayrollApplication application;

    @BeforeAll
    static void beforeAll() {
        GlobalDatabase.database = new PayrollDatabaseImplementation();
    }

    @BeforeEach
    void setUp() {
        GlobalDatabase.database.clear();
        application = new PayrollApplication();
    }

    @Test
    void testAddEmp() {
        application.setSource("test/casestudy/src/AddEmp.txt");
        application.execute();

        Employee e1 = GlobalDatabase.database.getEmployee(1);
        assertNotNull(e1);
        assertEquals("Bob", e1.getName());
        assertTrue(e1.getClassification() instanceof HourlyClassification);

        Employee e2 = GlobalDatabase.database.getEmployee(2);
        assertNotNull(e2);
        assertEquals("Bill", e2.getName());
        assertTrue(e2.getClassification() instanceof SalariedClassification);

        Employee e3 = GlobalDatabase.database.getEmployee(3);
        assertNotNull(e3);
        assertEquals("Alice", e3.getName());
        assertTrue(e3.getClassification() instanceof CommissionedClassification);
    }

    @Test
    void testDelEmp() {
        application.setSource("test/casestudy/src/AddEmp.txt");
        application.execute();
        Employee e = GlobalDatabase.database.getEmployee(1);
        assertNotNull(e);

        application.setSource("test/casestudy/src/DelEmp.txt");
        application.execute();
        e = GlobalDatabase.database.getEmployee(1);
        assertNull(e);
    }

    @Test
    void testTimeCard() {
        application.setSource("test/casestudy/src/AddEmp.txt");
        application.execute();

        application.setSource("test/casestudy/src/TimeCard.txt");
        application.execute();

        Employee e = GlobalDatabase.database.getEmployee(1);
        assertNotNull(e);

        HourlyClassification hc = (HourlyClassification) e.getClassification();
        TimeCard tc = hc.getTimeCard(LocalDate.of(2001, 10, 31));
        assertEquals(8.0, tc.getHours());
    }

    @Test
    void testSalesReceipt() {
        application.setSource("test/casestudy/src/AddEmp.txt");
        application.execute();

        application.setSource("test/casestudy/src/SalesReceipt.txt");
        application.execute();

        Employee e = GlobalDatabase.database.getEmployee(3);
        assertNotNull(e);

        CommissionedClassification cc = (CommissionedClassification) e.getClassification();
        SalesReceipt sr = cc.getSalesReceipt(LocalDate.of(2001, 10, 31));
        assertEquals(8.0, sr.getAmount());
    }

    @Test
    void testServiceCharge() {
        application.setSource("test/casestudy/src/AddEmp.txt");
        application.execute();

        Employee e = GlobalDatabase.database.getEmployee(1);
        assertNotNull(e);

        int memberId = 86;
        e.setAffiliation(new UnionAffiliation(memberId, 8.0));
        GlobalDatabase.database.addUnionMember(memberId, e);

        application.setSource("test/casestudy/src/ServiceCharge.txt");
        application.execute();

        UnionAffiliation uf = (UnionAffiliation) e.getAffiliation();
        double sc = uf.getServiceCharge(LocalDate.of(2001, 10, 31));
        assertEquals(12.95, sc, .001);
    }

    @Test
    void testChgEmp() {
        application.setSource("test/casestudy/src/AddEmp.txt");
        application.execute();

        application.setSource("test/casestudy/src/ChgEmp.txt");
        application.execute();

        Employee e1 = GlobalDatabase.database.getEmployee(1);
        assertEquals("Robert", e1.getName());
        assertEquals("nextHome", e1.getAddress());
        CommissionedClassification cc = (CommissionedClassification) e1.getClassification();
        assertEquals(2200.00, cc.getSalary());
        assertTrue(e1.getMethod() instanceof HoldMethod);
        assertEquals(7.5, cc.getRate());
        assertEquals(e1, GlobalDatabase.database.getUnionMember(86));

        Employee e2 = GlobalDatabase.database.getEmployee(2);
        HourlyClassification hc = (HourlyClassification) e2.getClassification();
        assertEquals(12.25, hc.getRate());
        assertTrue(e2.getMethod() instanceof DirectMethod);
        assertNull(GlobalDatabase.database.getUnionMember(87));

        Employee e3 = GlobalDatabase.database.getEmployee(3);
        SalariedClassification sc = (SalariedClassification) e3.getClassification();
        assertEquals(2000.00, sc.getSalary());
        assertTrue(e3.getMethod() instanceof MailMethod);
    }

    @Test
    void testPayday() {
        application.setSource("test/casestudy/src/AddEmp.txt");
        application.execute();

        PayrollFactory pf = new PayrollFactoryImplementation();
        TransactionFactory tf = new TransactionFactoryImplementation(pf);
        TransactionSource payDay = new TextParserTransactionSource("test/casestudy/src/Payday.txt", tf);
        List<Transaction> transactions = payDay.getTransactions();
        assertEquals(1, transactions.size());
        assertTrue(transactions.get(0) instanceof PaydayTransaction);
    }

    @Test
    void testMultipleCommand() {
        application.setSource("test/casestudy/src/Multi.txt");
        application.execute();

        Employee e = GlobalDatabase.database.getEmployee(1);
        HourlyClassification hc = (HourlyClassification) e.getClassification();
        assertEquals(15.24, hc.getRate());

        TimeCard tc = hc.getTimeCard(LocalDate.of(2001, 10, 31));
        assertEquals(8.0, tc.getHours());

        assertNotNull(GlobalDatabase.database.getUnionMember(86));
    }
}
