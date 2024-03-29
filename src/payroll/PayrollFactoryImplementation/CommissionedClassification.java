package payroll.PayrollFactoryImplementation;

import payroll.PayrollDomain.PayCheck;
import payroll.PayrollDomain.PaymentClassification;
import payroll.PayrollUtil.Date;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class CommissionedClassification implements PaymentClassification {
    private double itsSalary;
    private double itsCommissionRate;
    private Map<LocalDate, SalesReceipt> itsSalesReceipts = new HashMap<>();

    public CommissionedClassification(double salary, double commissionRate) {
        itsSalary = salary;
        itsCommissionRate = commissionRate;
    }

    public double getSalary() {
        return itsSalary;
    }

    public double getRate() {
        return itsCommissionRate;
    }

    public SalesReceipt getSalesReceipt(LocalDate date) {
        return itsSalesReceipts.get(date);
    }

    public void addSaleReceipt(LocalDate date, double amount) {
        SalesReceipt salesReceipt = new SalesReceipt(date, amount);
        itsSalesReceipts.put(salesReceipt.getDate(), salesReceipt);
    }

    @Override
    public double calculatePay(PayCheck pc) {
        double totalPay = itsSalary;
        for (SalesReceipt receipt : itsSalesReceipts.values()) {
            if (Date.isBetween(receipt.getDate(), pc.getPayPeriodStartDate(), pc.getPayPeriodEndDate())) {
                totalPay += receipt.getAmount() * itsCommissionRate;
            }
        }
        return totalPay;
    }
}
