package casestudy;

import java.util.Calendar;

public class MonthlySchedule implements PaymentSchedule {
    @Override
    public Calendar getPayPeriodStartDate(Calendar payDate) {
        Calendar startDate = Calendar.getInstance();
        startDate.setTime(payDate.getTime());
        startDate.set(Calendar.DAY_OF_MONTH, 1);
        return startDate;
    }

    @Override
    public boolean isPayDate(Calendar payDate) {
        return isLastDayOfMonth(payDate);
    }

    private boolean isLastDayOfMonth(Calendar date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date.getTime());
        return (cal.getActualMaximum(Calendar.DATE) == cal.get(Calendar.DATE));
    }
}
