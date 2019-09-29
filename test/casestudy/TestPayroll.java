package casestudy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

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
        assertEquals(1000.00, hc.getRate());

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
        assertEquals(50.0, cc.getRate());

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

        int memberId = 86;
        Affiliation af = new UnionAffiliation(memberId, 8.5);
        e.setAffiliation(af);

        PayrollDatabase.addUnionMember(memberId, e);

        ServiceChargeTransaction sct = new ServiceChargeTransaction(memberId, 20011031, 12.95);
        sct.execute();

        double sc = af.getServiceCharge(20011031);
        assertEquals(12.95, sc, .001);
    }

    @Test
    void testChangeNameTransaction() {
        int empId = 2;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
        t.execute();

        ChangeNameTransaction cnt = new ChangeNameTransaction(empId, "Bob");
        cnt.execute();

        Employee e = PayrollDatabase.getEmployee(empId);
        assertNotNull(e);
        assertEquals("Bob", e.getName());
    }

    @Test
    void testChangeAddressTransaction() {
        int empId = 2;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
        t.execute();

        ChangeAddressTransaction cnt = new ChangeAddressTransaction(empId, "City");
        cnt.execute();

        Employee e = PayrollDatabase.getEmployee(empId);
        assertNotNull(e);
        assertEquals("City", e.getAddress());
    }

    @Test
    void testChangeHourlyTransaction() {
        int empId = 3;
        AddCommissionedEmployee t = new AddCommissionedEmployee(empId, "Lance", "Home", 2500, 3.2);
        t.execute();

        ChangeHourlyTransaction cht = new ChangeHourlyTransaction(empId, 27.52);
        cht.execute();

        Employee e = PayrollDatabase.getEmployee(empId);
        assertNotNull(e);

        PaymentClassification pc = e.getClassification();
        HourlyClassification hc = (HourlyClassification) pc;
        assertNotNull(hc);
        assertEquals(27.52, hc.getRate());

        PaymentSchedule ps = e.getSchedule();
        WeeklySchedule ws = (WeeklySchedule) ps;
        assertNotNull(ws);
    }

    @Test
    void testChangeSalariedTransaction() {
        int empId = 3;
        AddCommissionedEmployee t = new AddCommissionedEmployee(empId, "Lance", "Home", 2500, 3.2);
        t.execute();

        ChangeSalariedTransaction cst = new ChangeSalariedTransaction(empId, 1000);
        cst.execute();

        Employee e = PayrollDatabase.getEmployee(empId);
        assertNotNull(e);

        PaymentClassification pc = e.getClassification();
        SalariedClassification sc = (SalariedClassification) pc;
        assertNotNull(sc);
        assertEquals(1000, sc.getSalary());

        PaymentSchedule ps = e.getSchedule();
        MonthlySchedule ms = (MonthlySchedule) ps;
        assertNotNull(ms);
    }

    @Test
    void testChangeCommissionedTransaction() {
        int empId = 3;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Lance", "Home", 25.72);
        t.execute();

        ChangeCommissionedTransaction cct = new ChangeCommissionedTransaction(empId, 2000, 3.2);
        cct.execute();

        Employee e = PayrollDatabase.getEmployee(empId);
        assertNotNull(e);

        PaymentClassification pc = e.getClassification();
        CommissionedClassification cc = (CommissionedClassification) pc;
        assertNotNull(cc);
        assertEquals(2000, cc.getSalary());
        assertEquals(3.2, cc.getRate());

        PaymentSchedule ps = e.getSchedule();
        BiweeklySchedule bs = (BiweeklySchedule) ps;
        assertNotNull(bs);
    }

    @Test
    void testChangeDirectTransaction() {
        int empId = 3;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Lance", "Home", 25.72);
        t.execute();

        ChangeDirectTransaction cdt = new ChangeDirectTransaction(empId, "bank", 10800);
        cdt.execute();

        Employee e = PayrollDatabase.getEmployee(empId);
        assertNotNull(e);

        PaymentMethod pm = e.getMethod();
        DirectMethod dm = (DirectMethod) pm;
        assertNotNull(dm);
        assertEquals("bank", dm.getBank());
        assertEquals(10800, dm.getAccount());
    }

    @Test
    void testChangeMailTransaction() {
        int empId = 3;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Lance", "Home", 25.72);
        t.execute();

        ChangeMailTransaction cmt = new ChangeMailTransaction(empId, "mailAddress");
        cmt.execute();

        Employee e = PayrollDatabase.getEmployee(empId);
        assertNotNull(e);

        PaymentMethod pm = e.getMethod();
        MailMethod mm = (MailMethod) pm;
        assertNotNull(mm);
        assertEquals("mailAddress", mm.getAddress());
    }

    @Test
    void testChangeHoldTransaction() {
        int empId = 3;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Lance", "Home", 25.72);
        t.execute();

        ChangeMailTransaction cmt = new ChangeMailTransaction(empId, "mailAddress");
        cmt.execute();
        Employee e = PayrollDatabase.getEmployee(empId);
        assertNotNull(e);
        assertTrue(e.getMethod() instanceof MailMethod);

        ChangeHoldTransaction cht = new ChangeHoldTransaction(empId);
        cht.execute();

        e = PayrollDatabase.getEmployee(empId);
        assertNotNull(e);

        PaymentMethod pm = e.getMethod();
        HoldMethod hm = (HoldMethod) pm;
        assertNotNull(hm);
    }

    @Test
    void testChangeMemberTransaction() {
        int empId = 2;
        int memberId = 7734;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
        t.execute();

        ChangeMemberTransaction cmt = new ChangeMemberTransaction(empId, memberId, 99.42);
        cmt.execute();

        Employee e = PayrollDatabase.getEmployee(empId);
        assertNotNull(e);

        Affiliation af = e.getAffiliation();
        UnionAffiliation uf = (UnionAffiliation) af;
        assertNotNull(uf);
        assertEquals(99.42, uf.getDues());

        Employee member = PayrollDatabase.getUnionMember(memberId);
        assertNotNull(member);
        assertEquals(e, member);
    }

    @Test
    void testChangeUnaffiliatedTransaction() {
        int empId = 2;
        int memberId = 7734;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
        t.execute();

        ChangeMemberTransaction cmt = new ChangeMemberTransaction(empId, memberId, 99.42);
        cmt.execute();

        Employee e = PayrollDatabase.getEmployee(empId);
        assertNotNull(e);

        Affiliation af = e.getAffiliation();
        UnionAffiliation uf = (UnionAffiliation) af;
        assertNotNull(uf);
        assertEquals(99.42, uf.getDues());

        Employee member = PayrollDatabase.getUnionMember(memberId);
        assertEquals(e, member);

        ChangeUnaffiliatedTransaction cut = new ChangeUnaffiliatedTransaction(empId);
        cut.execute();

        af = e.getAffiliation();
        NoAffiliation na = (NoAffiliation) af;
        member = PayrollDatabase.getUnionMember(memberId);
        assertEquals(na, member);
    }

    @Test
    void testPaySingleSalariedEmployee() {
        int empId = 1;
        AddSalariedEmployee t = new AddSalariedEmployee(empId, "Bob", "Home", 1000.0);
        t.execute();

        Calendar payDate = new GregorianCalendar(2001, Calendar.NOVEMBER, 30);
        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.execute();

        validatePayCheck(pt, empId, payDate, 1000.0);
    }

    @Test
    void testPaySingleSalariedEmployeeOnWrongDate() {
        int empId = 1;
        AddSalariedEmployee t = new AddSalariedEmployee(empId, "Bob", "Home", 1000.0);
        t.execute();

        Calendar payDate = new GregorianCalendar(2001, Calendar.NOVEMBER, 29);
        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.execute();

        PayCheck pc = pt.getPayCheck(empId);
        assertNull(pc);
    }

    private void validatePayCheck(PaydayTransaction pt, int empId, Calendar payDate, double pay) {
        PayCheck pc = pt.getPayCheck(empId);
        assertNotNull(pc);

        assertEquals(pc.getPayPeriodEndDate(), payDate);
        assertEquals(pay, pc.getGrossPay());
        assertEquals(0.0, pc.getDeductions());
        assertEquals(pay, pc.getNetPay());
    }
}
