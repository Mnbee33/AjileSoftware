package casestudy;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class CommissionedClassification implements PaymentClassification {
    private double itsSalary;
    private double itsCommissionRate;
    private Map<LocalDate, SaleReceipt> itsSaleReceipts = new HashMap<>();

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

    public SaleReceipt getSaleReceipt(LocalDate date) {
        return itsSaleReceipts.get(date);
    }

    public void addSaleReceipt(SaleReceipt saleReceipt) {
        itsSaleReceipts.put(saleReceipt.getDate(), saleReceipt);
    }

    @Override
    public double calculatePay(PayCheck pc) {
        double totalPay = itsSalary;
        for (SaleReceipt receipt : itsSaleReceipts.values()) {
            if (Date.isBetween(receipt.getDate(), pc.getPayPeriodStartDate(), pc.getPayPeriodEndDate())) {
                totalPay += receipt.getAmount() * itsCommissionRate;
            }
        }
        return totalPay;
    }
}
