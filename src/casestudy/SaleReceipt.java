package casestudy;

import java.time.LocalDate;

public class SaleReceipt {
    private LocalDate itsDate;
    private double itsAmount;

    public SaleReceipt(LocalDate date, double amount) {
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
