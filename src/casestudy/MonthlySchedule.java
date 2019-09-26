package casestudy;

import java.util.Calendar;

public class MonthlySchedule implements PaymentSchedule {
    @Override
    public Calendar getPayPeriodStartDate(Calendar payDate) {
        return null;
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
