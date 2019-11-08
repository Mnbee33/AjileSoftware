package casestudy;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class UnionAffiliation implements Affiliation {
    private double itsDues;
    private int itsMemberId;
    private Map<Calendar, ServiceCharge> itsServiceCharges = new HashMap<>();

    public UnionAffiliation(int memberId, double dues) {
        itsMemberId = memberId;
        itsDues = dues;
    }

    @Override
    public double getServiceCharge(Calendar date) {
        return itsServiceCharges.get(date).getAmount();
    }

    @Override
    public double calculateDeductions(PayCheck pc) {
        double totalServiceCharge = 0;
        double totalDues;
        for (ServiceCharge sc : itsServiceCharges.values()) {
            if (Date.isBetween(sc.getDate(), pc.getPayPeriodStartDate(), pc.getPayPeriodEndDate())) {
                totalServiceCharge += sc.getAmount();
            }
        }
        int fridays = numberOfFridaysInPeriod(pc.getPayPeriodStartDate(), pc.getPayPeriodEndDate());
        totalDues = itsDues * fridays;
        return totalDues + totalServiceCharge;
    }

    private int numberOfFridaysInPeriod(Calendar payPeriodStartDate, Calendar payPeriodEndDate) {
        int fridays = 0;
        Calendar cal = Calendar.getInstance();
        cal.setTime(payPeriodStartDate.getTime());
        while (cal.compareTo(payPeriodEndDate) <= 0) {
            if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
                fridays++;
            }
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }
        return fridays;
    }

    public void addServiceCharge(Calendar date, double amount) {
        itsServiceCharges.put(date, new ServiceCharge(date, amount));
    }

    public double getDues() {
        return itsDues;
    }

    public int getMemberId() {
        return itsMemberId;
    }

}
