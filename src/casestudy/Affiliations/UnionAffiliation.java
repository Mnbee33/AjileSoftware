package casestudy.Affiliations;

import casestudy.Payment.Date;
import casestudy.Payment.PayCheck;
import casestudy.PayrollDomain.Affiliation;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class UnionAffiliation implements Affiliation {
    private double itsDues;
    private int itsMemberId;
    private Map<LocalDate, ServiceCharge> itsServiceCharges = new HashMap<>();

    public UnionAffiliation(int memberId, double dues) {
        itsMemberId = memberId;
        itsDues = dues;
    }

    @Override
    public double getServiceCharge(LocalDate date) {
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

    private int numberOfFridaysInPeriod(LocalDate payPeriodStartDate, LocalDate payPeriodEndDate) {
        int fridays = 0;
        LocalDate date = payPeriodStartDate;
        while (!date.isAfter(payPeriodEndDate)) {
            if (date.getDayOfWeek().equals(DayOfWeek.FRIDAY)) {
                fridays++;
            }
            date = date.plusDays(1);
        }
        return fridays;
    }

    public void addServiceCharge(LocalDate date, double amount) {
        itsServiceCharges.put(date, new ServiceCharge(date, amount));
    }

    public double getDues() {
        return itsDues;
    }

    public int getMemberId() {
        return itsMemberId;
    }

}
