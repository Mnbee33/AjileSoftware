package casestudy.PayrollFactoryImplementation;

import java.time.LocalDate;

public class TimeCard {
    private LocalDate itsDate;
    private double itsHours;

    public TimeCard(LocalDate date, double hours) {
        itsDate = date;
        itsHours = hours;
    }

    public LocalDate getDate() {
        return itsDate;
    }

    public double getHours() {
        return itsHours;
    }
}
