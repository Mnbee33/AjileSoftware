package casestudy;

import casestudy.Affiliations.*;
import casestudy.ClassificationTransactions.*;
import casestudy.Classifications.*;
import casestudy.EmployeeAttributeTransactions.ChangeAddressTransaction;
import casestudy.EmployeeAttributeTransactions.ChangeNameTransaction;
import casestudy.GeneralTransactions.DeleteEmployeeTransaction;
import casestudy.MethodTransactions.ChangeDirectTransaction;
import casestudy.MethodTransactions.ChangeHoldTransaction;
import casestudy.MethodTransactions.ChangeMailTransaction;
import casestudy.Methods.DirectMethod;
import casestudy.Methods.HoldMethod;
import casestudy.Methods.MailMethod;
import casestudy.Payment.PayCheck;
import casestudy.Payment.PaydayTransaction;
import casestudy.PayrollDatabase.PayrollDatabase;
import casestudy.PayrollDomain.*;
import casestudy.Schedules.BiweeklySchedule;
import casestudy.Schedules.MonthlySchedule;
import casestudy.Schedules.WeeklySchedule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

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

        LocalDate date = LocalDate.of(2001, 10, 31);
        TimeCardTransaction tct = new TimeCardTransaction(date, 8.0, empId);
        tct.execute();

        Employee e = PayrollDatabase.getEmployee(empId);
        assertNotNull(e);

        PaymentClassification pc = e.getClassification();
        HourlyClassification hc = (HourlyClassification) pc;
        assertNotNull(hc);

        TimeCard tc = hc.getTimeCard(date);
        assertNotNull(tc);
        assertEquals(8.0, tc.getHours());
    }

    @Test
    void testSalesReceiptTransaction() {
        int empId = 2;
        AddCommissionedEmployee t = new AddCommissionedEmployee(empId, "Bill", "Home", 1000.00, 15.25);
        t.execute();

        LocalDate date = LocalDate.of(2001, 10, 31);
        SalesReceiptTransaction srt = new SalesReceiptTransaction(date, 8.0, empId);
        srt.execute();

        Employee e = PayrollDatabase.getEmployee(empId);
        assertNotNull(e);

        PaymentClassification pc = e.getClassification();
        CommissionedClassification cc = (CommissionedClassification) pc;
        assertNotNull(cc);

        SalesReceipt sr = cc.getSalesReceipt(date);
        assertNotNull(sr);
        assertEquals(8.0, sr.getAmount());
    }

    @Test
    void testAddServiceCharge() {
        int empId = 2;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
        t.execute();

        LocalDate payDate = LocalDate.of(2001, 10, 31);
        TimeCardTransaction tct = new TimeCardTransaction(payDate, 8.0, empId);
        tct.execute();

        Employee e = PayrollDatabase.getEmployee(empId);
        assertNotNull(e);

        int memberId = 86;
        Affiliation af = new UnionAffiliation(memberId, 8.5);
        e.setAffiliation(af);

        PayrollDatabase.addUnionMember(memberId, e);

        ServiceChargeTransaction sct = new ServiceChargeTransaction(memberId, payDate, 12.95);
        sct.execute();

        double sc = af.getServiceCharge(payDate);
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

        LocalDate payDate = LocalDate.of(2001, 11, 30);
        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.execute();

        validatePayCheck(pt, empId, payDate, 1000.0);
    }

    @Test
    void testPaySingleSalariedEmployeeOnWrongDate() {
        int empId = 1;
        AddSalariedEmployee t = new AddSalariedEmployee(empId, "Bob", "Home", 1000.0);
        t.execute();

        LocalDate payDate = LocalDate.of(2001, 11, 29);
        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.execute();

        PayCheck pc = pt.getPayCheck(empId);
        assertNull(pc);
    }

    @Test
    void testPaySingleHourlyEmployeeNoTimeCards() {
        int empId = 2;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
        t.execute();

        LocalDate payDate = LocalDate.of(2001, 11, 9);
        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.execute();

        validatePayCheck(pt, empId, payDate, 0.0);
    }

    @Test
    void testPaySingleHourlyEmployeeOneTimeCard() {
        int empId = 2;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
        t.execute();

        LocalDate payDate = LocalDate.of(2001, 11, 9);
        TimeCardTransaction tc = new TimeCardTransaction(payDate, 2.0, empId);
        tc.execute();

        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.execute();

        validatePayCheck(pt, empId, payDate, 30.5);
    }

    @Test
    void testPaySingleHourlyEmployeeOverOneTimeCard() {
        int empId = 2;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
        t.execute();

        LocalDate payDate = LocalDate.of(2001, 11, 9);
        TimeCardTransaction tc = new TimeCardTransaction(payDate, 9.0, empId);
        tc.execute();

        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.execute();

        validatePayCheck(pt, empId, payDate, (8 + 1.5) * 15.25);
    }

    @Test
    void testPaySingleHourlyEmployeeOnWrongDate() {
        int empId = 2;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
        t.execute();

        LocalDate payDate = LocalDate.of(2001, 11, 8);
        TimeCardTransaction tc = new TimeCardTransaction(payDate, 9.0, empId);
        tc.execute();

        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.execute();

        PayCheck pc = pt.getPayCheck(empId);
        assertNull(pc);
    }

    @Test
    void testPaySingleHourlyEmployeeTwoTimeCards() {
        int empId = 2;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
        t.execute();

        LocalDate payDate = LocalDate.of(2001, 11, 9);
        TimeCardTransaction tc = new TimeCardTransaction(payDate, 2.0, empId);
        tc.execute();

        LocalDate payDate2 = LocalDate.of(2001, 11, 8);
        TimeCardTransaction tc2 = new TimeCardTransaction(payDate2, 5.0, empId);
        tc2.execute();

        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.execute();

        validatePayCheck(pt, empId, payDate, 7 * 15.25);
    }

    @Test
    void testPaySingleHourlyEmployeeWithTimeCardsSpanningTwoPayPeriod() {
        int empId = 2;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
        t.execute();

        LocalDate payDate = LocalDate.of(2001, 11, 9);
        LocalDate dateInPreviousPayPeriod = LocalDate.of(2001, 11, 2);

        TimeCardTransaction tc = new TimeCardTransaction(payDate, 2.0, empId);
        tc.execute();

        TimeCardTransaction tc2 = new TimeCardTransaction(dateInPreviousPayPeriod, 5.0, empId);
        tc2.execute();

        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.execute();

        validatePayCheck(pt, empId, payDate, 2 * 15.25);
    }

    @Test
    void testPayCommissionedEmployeeNoSalesReceipts() {
        int empId = 2;
        AddCommissionedEmployee t = new AddCommissionedEmployee(empId, "Lance", "Home", 2500, 3.2);
        t.execute();

        LocalDate payDate = LocalDate.of(2001, 11, 9);

        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.execute();

        validatePayCheck(pt, empId, payDate, 2500);
    }

    @Test
    void testPayCommissionedEmployeeOneSalesReceipt() {
        int empId = 2;
        AddCommissionedEmployee t = new AddCommissionedEmployee(empId, "Lance", "Home", 2500, 3.2);
        t.execute();

        LocalDate payDate = LocalDate.of(2001, 11, 9);

        SalesReceiptTransaction srt = new SalesReceiptTransaction(payDate, 8.0, empId);
        srt.execute();

        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.execute();

        validatePayCheck(pt, empId, payDate, 2500 + 8.0 * 3.2);
    }

    @Test
    void testPayCommissionedEmployeeTwoSalesReceipt() {
        int empId = 2;
        AddCommissionedEmployee t = new AddCommissionedEmployee(empId, "Lance", "Home", 2500, 3.2);
        t.execute();

        LocalDate payDate = LocalDate.of(2001, 11, 9);
        SalesReceiptTransaction srt = new SalesReceiptTransaction(payDate, 8.0, empId);
        srt.execute();

        SalesReceiptTransaction srt2 = new SalesReceiptTransaction(
                LocalDate.of(2001, 11, 8), 7.0, empId);
        srt2.execute();

        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.execute();

        validatePayCheck(pt, empId, payDate, 2500 + ((8.0 + 7.0) * 3.2));
    }

    @Test
    void testPayCommissionedEmployeeOnWrongDate() {
        int empId = 2;
        AddCommissionedEmployee t = new AddCommissionedEmployee(empId, "Lance", "Home", 2500, 3.2);
        t.execute();

        LocalDate payDate = LocalDate.of(2001, 11, 16);
        SalesReceiptTransaction srt = new SalesReceiptTransaction(payDate, 8.0, empId);
        srt.execute();

        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.execute();

        PayCheck pc = pt.getPayCheck(empId);
        assertNull(pc);
    }

    @Test
    void testPayCommissionedEmployeeMultiSalesReceipts() {
        int empId = 2;
        AddCommissionedEmployee t = new AddCommissionedEmployee(empId, "Lance", "Home", 2500, 3.2);
        t.execute();

        LocalDate payDate = LocalDate.of(2001, 11, 9);
        SalesReceiptTransaction srt = new SalesReceiptTransaction(payDate, 8.0, empId);
        srt.execute();

        SalesReceiptTransaction srt2 = new SalesReceiptTransaction(
                LocalDate.of(2001, 10, 26), 7.0, empId);
        srt2.execute();

        SalesReceiptTransaction srt3 = new SalesReceiptTransaction(
                LocalDate.of(2001, 12, 10), 6.0, empId);
        srt3.execute();

        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.execute();

        validatePayCheck(pt, empId, payDate, 2500 + (8.0 * 3.2));
    }

    @Test
    void testSalariedUnionMemberDues() {
        int empId = 1;
        AddSalariedEmployee t = new AddSalariedEmployee(empId, "Bob", "Home", 1000.0);
        t.execute();

        int memberId = 7734;
        ChangeMemberTransaction cmt = new ChangeMemberTransaction(empId, memberId, 9.42);
        cmt.execute();

        LocalDate payDate = LocalDate.of(2001, 11, 30);
        int fridays = 5;    // 2001年11月に金曜日は5日あった
        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.execute();

        PayCheck pc = pt.getPayCheck(empId);
        assertNotNull(pc);
        assertEquals(payDate, pc.getPayPeriodEndDate());
        assertEquals(1000.0, pc.getGrossPay());
        assertEquals("Hold", pc.getField("Disposition"));
        assertEquals(fridays * 9.42, pc.getDeductions());
        assertEquals(1000.0 - (fridays * 9.42), pc.getNetPay());
    }

    @Test
    void testHourlyUnionMemberServiceCharge() {
        int empId = 1;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "Home", 15.24);
        t.execute();

        int memberId = 7734;
        ChangeMemberTransaction cmt = new ChangeMemberTransaction(empId, memberId, 9.42);
        cmt.execute();

        LocalDate payDate = LocalDate.of(2001, 11, 9);
        ServiceChargeTransaction sct = new ServiceChargeTransaction(memberId, payDate, 19.42);
        sct.execute();

        TimeCardTransaction tct = new TimeCardTransaction(payDate, 8.0, empId);
        tct.execute();

        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.execute();

        PayCheck pc = pt.getPayCheck(empId);
        assertNotNull(pc);
        assertEquals(payDate, pc.getPayPeriodEndDate());
        assertEquals(8 * 15.24, pc.getGrossPay());
        assertEquals("Hold", pc.getField("Disposition"));
        assertEquals(9.42 + 19.42, pc.getDeductions());
        assertEquals(8 * 15.24 - (9.42 + 19.42), pc.getNetPay());
    }

    @Test
    void testServiceChargesSpanningMultiplePayPeriod() {
        int empId = 1;
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "Home", 15.24);
        t.execute();

        int memberId = 7734;
        ChangeMemberTransaction cmt = new ChangeMemberTransaction(empId, memberId, 9.42);
        cmt.execute();

        LocalDate earlyDate = LocalDate.of(2001, 11, 2); // 前の金曜日
        LocalDate payDate = LocalDate.of(2001, 11, 9);
        LocalDate lateDate = LocalDate.of(2001, 11, 16);  // 次の金曜日

        ServiceChargeTransaction sct = new ServiceChargeTransaction(memberId, payDate, 19.42);
        sct.execute();

        ServiceChargeTransaction sctEarly = new ServiceChargeTransaction(memberId, earlyDate, 100.00);
        sctEarly.execute();

        ServiceChargeTransaction sctLate = new ServiceChargeTransaction(memberId, lateDate, 100.00);
        sctLate.execute();

        TimeCardTransaction tct = new TimeCardTransaction(payDate, 8.0, empId);
        tct.execute();

        PaydayTransaction pt = new PaydayTransaction(payDate);
        pt.execute();

        PayCheck pc = pt.getPayCheck(empId);
        assertNotNull(pc);
        assertEquals(payDate, pc.getPayPeriodEndDate());
        assertEquals(8 * 15.24, pc.getGrossPay());
        assertEquals("Hold", pc.getField("Disposition"));
        assertEquals(9.42 + 19.42, pc.getDeductions());
        assertEquals(8 * 15.24 - (9.42 + 19.42), pc.getNetPay());
    }

    private void validatePayCheck(PaydayTransaction pt, int empId, LocalDate payDate, double pay) {
        PayCheck pc = pt.getPayCheck(empId);
        assertNotNull(pc);

        assertEquals(pc.getPayPeriodEndDate(), payDate);
        assertEquals(pay, pc.getGrossPay());
        assertEquals("Hold", pc.getField("Disposition"));
        assertEquals(0.0, pc.getDeductions());
        assertEquals(pay, pc.getNetPay());
    }
}
