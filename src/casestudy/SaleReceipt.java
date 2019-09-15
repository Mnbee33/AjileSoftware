package casestudy;

public class SaleReceipt {
    private long itsDate;
    private double itsAmount;

    public SaleReceipt(long date, double amount) {
        itsDate = date;
        itsAmount = amount;
    }

    public long getDate() {
        return itsDate;
    }

    public double getAmount() {
        return itsAmount;
    }
}
