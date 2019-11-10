package casestudy.Affiliations;

import java.time.LocalDate;

public class ServiceCharge {
    private LocalDate itsDate;
    private double itsAmount;

    public ServiceCharge(LocalDate date, double amount) {
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
