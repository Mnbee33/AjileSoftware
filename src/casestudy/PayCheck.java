package casestudy;

import java.util.Calendar;

public class PayCheck {
    private Calendar itsPayPeriodStartDate;
    private Calendar itsPayPeriodEndDate;

    private double itsGrossPay;
    private double itsDeductions;
    private double itsNetPay;

    public PayCheck(Calendar payPeriodStartDate, Calendar payPeriodEndDate) {
        itsPayPeriodStartDate = payPeriodStartDate;
        itsPayPeriodEndDate = payPeriodEndDate;
    }

    public void setGrossPay(double grossPay) {
        itsGrossPay = grossPay;
    }

    public void setDeductions(double deductions) {
        itsDeductions = deductions;
    }

    public void netPay(double netPay) {
        itsNetPay = netPay;
    }

    public double getGrossPay() {
        return itsGrossPay;
    }

    public double getDeductions() {
        return itsDeductions;
    }

    public double getNetPay() {
        return itsNetPay;
    }

    public Calendar getPayPeriodEndDate() {
        return itsPayPeriodEndDate;
    }

    public String getField(String str) {
        return "Hold";
    }

    public Calendar getPayPeriodStartDate() {
        return itsPayPeriodStartDate;
    }
}
