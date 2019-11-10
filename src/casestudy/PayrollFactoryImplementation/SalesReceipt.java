package casestudy.PayrollFactoryImplementation;

import java.time.LocalDate;

public class SalesReceipt {
    private LocalDate itsDate;
    private double itsAmount;

    public SalesReceipt(LocalDate date, double amount) {
        itsDate = date;
        itsAmount = amount;
    }

    public LocalDate getDate() {
        return itsDate;
    }

    public double getAmount() {
        return itsAmount;
    }
}
