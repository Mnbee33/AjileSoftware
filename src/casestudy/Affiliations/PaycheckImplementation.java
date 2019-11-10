package casestudy.Affiliations;

import casestudy.PayrollDomain.PayCheck;

import java.time.LocalDate;

public class PaycheckImplementation implements PayCheck {
    private LocalDate itsPayPeriodStartDate;
    private LocalDate itsPayPeriodEndDate;

    private double itsGrossPay;
    private double itsDeductions;
    private double itsNetPay;

    public PaycheckImplementation(LocalDate payPeriodStartDate, LocalDate payPeriodEndDate) {
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

    public LocalDate getPayPeriodEndDate() {
        return itsPayPeriodEndDate;
    }

    public String getField(String str) {
        return "Hold";
    }

    public LocalDate getPayPeriodStartDate() {
        return itsPayPeriodStartDate;
    }
}
