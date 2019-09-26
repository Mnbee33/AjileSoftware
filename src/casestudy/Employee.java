package casestudy;

import java.util.Calendar;

public class Employee {
    private int itsEmpId;
    private String itsName;
    private String itsAddress;

    private PaymentClassification itsClassification;
    private PaymentSchedule itsSchedule;
    private PaymentMethod itsMethod;

    private Affiliation itsAffiliation;

    public Employee(int empId, String name, String address) {
        itsEmpId = empId;
        itsName = name;
        itsAddress = address;
    }

    public void setClassification(PaymentClassification pc) {
        itsClassification = pc;
    }

    public PaymentClassification getClassification() {
        return itsClassification;
    }

    public void setSchedule(PaymentSchedule ps) {
        itsSchedule = ps;
    }

    public PaymentSchedule getSchedule() {
        return itsSchedule;
    }

    public void setMethod(PaymentMethod pm) {
        itsMethod = pm;
    }

    public PaymentMethod getMethod() {
        return itsMethod;
    }

    public void setName(String name) {
        itsName = name;
    }

    public String getName() {
        return itsName;
    }

    public void setAffiliation(Affiliation af) {
        itsAffiliation = af;
    }

    public Affiliation getAffiliation() {
        return itsAffiliation;
    }

    public String getAddress() {
        return itsAddress;
    }

    public void setAddress(String address) {
        itsAddress = address;
    }

    public boolean isPayDate(Calendar payDate) {
        return itsSchedule.isPayDate(payDate);
    }

    public Calendar getPayPeriodStartDate(Calendar payDate) {
        return itsSchedule.getPayPeriodStartDate(payDate);
    }

    public void payday(PayCheck pc) {
        double grossPay = itsClassification.calculatePay(pc);
        double deductions = itsAffiliation.calculateDeductions(pc);
        double netPay = grossPay - deductions;
        pc.setGrossPay(grossPay);
        pc.setDeductions(deductions);
        pc.netPay(netPay);
        itsMethod.pay(pc);
    }
}
