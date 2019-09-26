package casestudy;

import java.util.Calendar;

public class BiweeklySchedule implements PaymentSchedule {
    @Override
    public Calendar getPayPeriodStartDate(Calendar payDate) {
        return null;
    }

    @Override
    public boolean isPayDate(Calendar payDate) {
        return false;
    }
}
