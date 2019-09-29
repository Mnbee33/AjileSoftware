package casestudy;

import java.util.HashMap;
import java.util.Map;

public class CommissionedClassification implements PaymentClassification {
    private double itsSalary;
    private double itsCommissionRate;
    private Map<Long, SaleReceipt> itsSaleReceipts = new HashMap<>();

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
        return itsSaleReceipts.get(date);
    }

    public void addSaleReceipt(SaleReceipt saleReceipt) {
        itsSaleReceipts.put(saleReceipt.getDate(), saleReceipt);
    }

    @Override
    public double calculatePay(PayCheck pc) {
        return 0;
    }
}
