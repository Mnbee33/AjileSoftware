package casestudy;

import java.util.Calendar;

public class SaleReceipt {
    private Calendar itsDate;
    private double itsAmount;

    public SaleReceipt(Calendar date, double amount) {
        itsDate = date;
        itsAmount = amount;
    }

    public Calendar getDate() {
        return itsDate;
    }

    public double getAmount() {
        return itsAmount;
    }
}
