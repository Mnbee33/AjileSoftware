package casestudy;

import java.time.LocalDate;

public class MonthlySchedule implements PaymentSchedule {
    @Override
    public LocalDate getPayPeriodStartDate(LocalDate payDate) {
        return payDate.withDayOfMonth(1);
    }

    @Override
    public boolean isPayDate(LocalDate payDate) {
        return isLastDayOfMonth(payDate);
    }

    private boolean isLastDayOfMonth(LocalDate date) {
        return date.getDayOfMonth() == date.getMonth().maxLength();
    }
}
