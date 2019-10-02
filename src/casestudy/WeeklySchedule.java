package casestudy;

import java.util.Calendar;

public class WeeklySchedule implements PaymentSchedule {
    @Override
    public Calendar getPayPeriodStartDate(Calendar payDate) {
        Calendar payPeriodStartDate = Calendar.getInstance();
        payPeriodStartDate.setTime(payDate.getTime());
        payPeriodStartDate.add(Calendar.DATE, -6);
        return payPeriodStartDate;
    }

    @Override
    public boolean isPayDate(Calendar payDate) {
        return payDate.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY;
    }
}
