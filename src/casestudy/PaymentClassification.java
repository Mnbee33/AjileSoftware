package casestudy;

import java.util.Calendar;

public interface PaymentClassification {
    double calculatePay(PayCheck pc);

    default boolean isPayDate(Calendar theDate, PayCheck pc) {
        Calendar payPeriodStartDate = pc.getPayPeriodStartDate();
        Calendar payPeriodEndDate = pc.getPayPeriodEndDate();
        return theDate.compareTo(payPeriodStartDate) >= 0 &&
                theDate.compareTo(payPeriodEndDate) <= 0;
    }
}
