package casestudy;

import java.util.HashMap;
import java.util.Map;

public class HourlyClassification implements PaymentClassification {
    private double itsHourlyRate;
    private Map<Long, TimeCard> timeCards = new HashMap<>();

    public HourlyClassification(double hourlyRate) {
        itsHourlyRate = hourlyRate;
    }

    public double getRate() {
        return itsHourlyRate;
    }

    public TimeCard getTimeCard(long date) {
        return timeCards.get(date);
    }

    public void addTimeCard(TimeCard timeCard) {
        timeCards.put(timeCard.getDate(), timeCard);
    }
}
