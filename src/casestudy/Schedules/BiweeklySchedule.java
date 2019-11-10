package casestudy.Schedules;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class BiweeklySchedule implements PaymentSchedule {
    private final LocalDate FIRST_PAYABLE_FRIDAY = LocalDate.of(2001, 11, 9);


    @Override
    public LocalDate getPayPeriodStartDate(LocalDate payDate) {
        return payDate.minusDays(13);
    }

    @Override
    public boolean isPayDate(LocalDate payDate) {
        LocalDate date = FIRST_PAYABLE_FRIDAY;
        if (payDate.getDayOfWeek().equals(DayOfWeek.FRIDAY)) {
            while (!date.isAfter(payDate)) {
                if (date.isEqual(payDate)) {
                    return true;
                }
                date = date.plusWeeks(2);
            }
        }
        return false;
    }
}
