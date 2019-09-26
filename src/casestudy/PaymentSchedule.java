package casestudy;

import java.util.Calendar;

public interface PaymentSchedule {
    Calendar getPayPeriodStartDate(Calendar payDate);

    boolean isPayDate(Calendar payDate);
}
