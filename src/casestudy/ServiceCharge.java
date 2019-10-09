package casestudy;

import java.util.Calendar;

public class ServiceCharge {
    private Calendar itsDate;
    private double itsAmount;

    public ServiceCharge(Calendar date, double amount) {
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
