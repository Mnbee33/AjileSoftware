package casestudy.Classifications;

import casestudy.Transactions.PayCheck;

public class SalariedClassification implements PaymentClassification {
    private double itsSalary;

    public SalariedClassification(double salary) {
        itsSalary = salary;
    }

    public double getSalary() {
        return itsSalary;
    }

    @Override
    public double calculatePay(PayCheck pc) {
        return itsSalary;
    }
}
