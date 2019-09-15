package casestudy;

public class TimeCard {
    private long itsDate;
    private double itsHours;

    public TimeCard(long date, double hours) {
        itsDate = date;
        itsHours = hours;
    }

    public long getDate() {
        return itsDate;
    }

    public double getHours() {
        return itsHours;
    }
}
