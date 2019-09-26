package casestudy;

import java.util.Calendar;

public class WeeklySchedule implements PaymentSchedule {
    @Override
    public Calendar getPayPeriodStartDate(Calendar payDate) {
        return null;
    }

    @Override
    public boolean isPayDate(Calendar payDate) {
        return false;
    }
}
