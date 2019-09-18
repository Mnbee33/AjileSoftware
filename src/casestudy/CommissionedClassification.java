package casestudy;

import java.util.HashMap;
import java.util.Map;

public class CommissionedClassification implements PaymentClassification {
    private double itsSalary;
    private double itsCommissionRate;
    private Map<Long, SaleReceipt> saleReceipts = new HashMap<>();

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

    public SaleReceipt getSaleReceipt(long date) {
        return saleReceipts.get(date);
    }

    public void addSaleReceipt(SaleReceipt saleReceipt) {
        saleReceipts.put(saleReceipt.getDate(), saleReceipt);
    }
}
