package casestudy;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class HourlyClassification implements PaymentClassification {
    private double itsHourlyRate;
    private Map<Calendar, TimeCard> timeCards = new HashMap<>();

    public HourlyClassification(double hourlyRate) {
        itsHourlyRate = hourlyRate;
    }

    public double getRate() {
        return itsHourlyRate;
    }

    public TimeCard getTimeCard(Calendar date) {
        return timeCards.get(date);
    }

    public void addTimeCard(TimeCard timeCard) {
        timeCards.put(timeCard.getDate(), timeCard);
    }

    @Override
    public double calculatePay(PayCheck pc) {
        double totalPay = 0;
        for (TimeCard tc : timeCards.values()) {
            if (isInPayPeriod(pc, tc)) {
                if (tc.getHours() > 8) {
                    totalPay += itsHourlyRate * 8 + itsHourlyRate * (tc.getHours() - 8) * 1.5;
                } else {
                    totalPay += itsHourlyRate * tc.getHours();
                }
            }
        }
        return totalPay;
    }

    private boolean isInPayPeriod(PayCheck pc, TimeCard tc) {
        Calendar timeCardDate = tc.getDate();
        Calendar payPeriodEndDate = (Calendar) pc.getPayPeriodEndDate().clone();
        Calendar payPeriodStartDate = (Calendar) pc.getPayPeriodEndDate().clone();
        payPeriodStartDate.add(Calendar.DATE, -5);
        return (timeCardDate.compareTo(payPeriodStartDate) >= 0)
                && (timeCardDate.compareTo(payPeriodEndDate) <= 0);
    }
}
