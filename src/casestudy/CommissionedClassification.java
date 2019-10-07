package casestudy;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class CommissionedClassification implements PaymentClassification {
    private double itsSalary;
    private double itsCommissionRate;
    private Map<Calendar, SaleReceipt> itsSaleReceipts = new HashMap<>();

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

    public SaleReceipt getSaleReceipt(Calendar date) {
        return itsSaleReceipts.get(date);
    }

    public void addSaleReceipt(SaleReceipt saleReceipt) {
        itsSaleReceipts.put(saleReceipt.getDate(), saleReceipt);
    }

    @Override
    public double calculatePay(PayCheck pc) {
        double totalPay = itsSalary;
        for (SaleReceipt receipt : itsSaleReceipts.values()) {
            if (isPayPeriod(pc, receipt)) {
                totalPay += receipt.getAmount() * itsCommissionRate;
            }
        }
        return totalPay;
    }

    private boolean isPayPeriod(PayCheck pc, SaleReceipt receipt) {
        return (receipt.getDate().compareTo(pc.getPayPeriodStartDate()) >= 0)
                && (receipt.getDate().compareTo(pc.getPayPeriodEndDate()) <= 0);
    }
}
