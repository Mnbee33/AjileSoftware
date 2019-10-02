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
            totalPay += itsHourlyRate * tc.getHours();
        }
        return totalPay;
    }
}
