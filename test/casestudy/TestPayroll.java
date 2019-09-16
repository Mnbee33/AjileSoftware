package casestudy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestPayroll {
    @BeforeEach
    void setUp() {
        PayrollDatabase.clear();
    }

    @Test
    void testAddSalariedEmployee() {
        int empId = 1;
        AddSalariedEmployee t = new AddSalariedEmployee(empId, "Bob", "Home", 1000.00);
        t.execute();

        Employee e = PayrollDatabase.getEmployee(empId);
        assertNotNull(e);
        assertEquals("Bob", e.getName());

        PaymentClassification pc = e.getClassification();
        SalariedClassification sc = (SalariedClassification) pc;
        assertNotNull(sc);
        assertEquals(1000.00, sc.getSalary());

        PaymentSchedule ps = e.getSchedule();
        MonthlySchedule ms = (MonthlySchedule) ps;
        assertNotNull(ms);

        PaymentMethod pm = e.getMethod();
        HoldMethod hm = (HoldMethod) pm;
        assertNotNull(hm);
    }

    @Test
    void testAddHourlyEmployee() {
        int empId = 1;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bob", "Home", 1000.00);
        t.execute();

        Employee e = PayrollDatabase.getEmployee(empId);
        assertNotNull(e);
        assertEquals("Bob", e.getName());

        PaymentClassification pc = e.getClassification();
        HourlyClassification hc = (HourlyClassification) pc;
        assertNotNull(hc);
        assertEquals(1000.00, hc.getHourlyRate());

        PaymentSchedule ps = e.getSchedule();
        WeeklySchedule ws = (WeeklySchedule) ps;
        assertNotNull(ws);

        PaymentMethod pm = e.getMethod();
        HoldMethod hm = (HoldMethod) pm;
        assertNotNull(hm);
    }

    @Test
    void testAddCommissionedEmployee() {
        int empId = 1;
        AddCommissionedEmployee t = new AddCommissionedEmployee(empId, "Bob", "Home", 1000.00, 50.0);
        t.execute();

        Employee e = PayrollDatabase.getEmployee(empId);
        assertNotNull(e);
        assertEquals("Bob", e.getName());

        PaymentClassification pc = e.getClassification();
        CommissionedClassification cc = (CommissionedClassification) pc;
        assertNotNull(cc);
        assertEquals(1000.00, cc.getSalary());
        assertEquals(50.0, cc.getCommissionRate());

        PaymentSchedule ps = e.getSchedule();
        BiweeklySchedule bs = (BiweeklySchedule) ps;
        assertNotNull(bs);

        PaymentMethod pm = e.getMethod();
        HoldMethod hm = (HoldMethod) pm;
        assertNotNull(hm);
    }

    @Test
    void testDeleteEmployee() {
        int empId = 3;
        AddCommissionedEmployee t = new AddCommissionedEmployee(empId, "Lance", "Home", 2500, 3.2);
        t.execute();

        Employee e = PayrollDatabase.getEmployee(empId);
        assertNotNull(e);

        DeleteEmployeeTransaction dt = new DeleteEmployeeTransaction(empId);
        dt.execute();

        e = PayrollDatabase.getEmployee(empId);
        assertNull(e);
    }

    @Test
    void testTimeCardTransaction() {
        int empId = 2;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
        t.execute();

        TimeCardTransaction tct = new TimeCardTransaction(20011031, 8.0, empId);
        tct.execute();

        Employee e = PayrollDatabase.getEmployee(empId);
        assertNotNull(e);

        PaymentClassification pc = e.getClassification();
        HourlyClassification hc = (HourlyClassification) pc;
        assertNotNull(hc);

        TimeCard tc = hc.getTimeCard(20011031);
        assertNotNull(tc);
        assertEquals(8.0, tc.getHours());
    }

    @Test
    void testSalesReceiptTransaction() {
        int empId = 2;
        AddCommissionedEmployee t = new AddCommissionedEmployee(empId, "Bill", "Home", 1000.00, 15.25);
        t.execute();

        SalesReceiptTransaction srt = new SalesReceiptTransaction(20011031, 8.0, empId);
        srt.execute();

        Employee e = PayrollDatabase.getEmployee(empId);
        assertNotNull(e);

        PaymentClassification pc = e.getClassification();
        CommissionedClassification cc = (CommissionedClassification) pc;
        assertNotNull(cc);

        SaleReceipt sr = cc.getSaleReceipt(20011031);
        assertNotNull(sr);
        assertEquals(8.0, sr.getAmount());
    }

    @Test
    void testAddServiceCharge() {
        int empId = 2;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
        t.execute();

        TimeCardTransaction tct = new TimeCardTransaction(20011031, 8.0, empId);
        tct.execute();

        Employee e = PayrollDatabase.getEmployee(empId);
        assertNotNull(e);

        Affiliation af = new UnionAffiliation(12.5);
        e.setAffiliation(af);

        int memberId = 86;
        PayrollDatabase.addUnionMember(memberId, e);

        ServiceChargeTransaction sct = new ServiceChargeTransaction(memberId, 20011031, 12.95);
        sct.execute();

        double sc = af.getServiceCharge(20011031);
        assertEquals(12.95, sc, .001);
    }
}
