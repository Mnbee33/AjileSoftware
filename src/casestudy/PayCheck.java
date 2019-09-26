package casestudy;

import java.util.Calendar;

public class PayCheck {
    private Calendar itsPayPeriodStartDate;
    private Calendar itsPayDate;

    private double itsGrossPay;
    private double itsDeductions;
    private double itsNetPay;

    public PayCheck(Calendar payPeriodStartDate, Calendar payDate) {
        itsPayPeriodStartDate = payPeriodStartDate;
        itsPayDate = payDate;
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
}
