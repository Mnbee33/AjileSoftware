package casestudy.Classifications;

import casestudy.Payment.Date;
import casestudy.Payment.PayCheck;
import casestudy.PayrollDomain.PaymentClassification;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class HourlyClassification implements PaymentClassification {
    private double itsHourlyRate;
    private Map<LocalDate, TimeCard> timeCards = new HashMap<>();

    public HourlyClassification(double hourlyRate) {
        itsHourlyRate = hourlyRate;
    }

    public double getRate() {
        return itsHourlyRate;
    }

    public TimeCard getTimeCard(LocalDate date) {
        return timeCards.get(date);
    }

    public void addTimeCard(LocalDate date, double hours) {
        TimeCard timeCard = new TimeCard(date, hours);
        timeCards.put(timeCard.getDate(), timeCard);
    }

    @Override
    public double calculatePay(PayCheck pc) {
        double totalPay = 0;
        for (TimeCard tc : timeCards.values()) {
            if (Date.isBetween(tc.getDate(), pc.getPayPeriodStartDate(), pc.getPayPeriodEndDate())) {
                if (tc.getHours() > 8) {
                    totalPay += itsHourlyRate * 8 + itsHourlyRate * (tc.getHours() - 8) * 1.5;
                } else {
                    totalPay += itsHourlyRate * tc.getHours();
                }
            }
        }
        return totalPay;
    }
}
