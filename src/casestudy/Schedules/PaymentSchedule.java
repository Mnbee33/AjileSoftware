package casestudy.Schedules;

import java.time.LocalDate;

public interface PaymentSchedule {
    LocalDate getPayPeriodStartDate(LocalDate payDate);

    boolean isPayDate(LocalDate theDate);
}
