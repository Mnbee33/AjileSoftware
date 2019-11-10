package casestudy.Schedules;

import casestudy.PayrollDomain.PaymentSchedule;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeeklySchedule implements PaymentSchedule {
    @Override
    public LocalDate getPayPeriodStartDate(LocalDate payDate) {
        return payDate.minusDays(6);
    }

    @Override
    public boolean isPayDate(LocalDate payDate) {
        return payDate.getDayOfWeek().equals(DayOfWeek.FRIDAY);
    }
}
