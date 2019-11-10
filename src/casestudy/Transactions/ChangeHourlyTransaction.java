package casestudy.Transactions;

import casestudy.Classifications.HourlyClassification;
import casestudy.Classifications.PaymentClassification;
import casestudy.Schedules.PaymentSchedule;
import casestudy.Schedules.WeeklySchedule;

public class ChangeHourlyTransaction extends ChangeClassificationTransaction {
    private double itsRate;

    public ChangeHourlyTransaction(int empId, double rate) {
        super(empId);
        itsRate = rate;
    }

    @Override
    PaymentSchedule getSchedule() {
        return new WeeklySchedule();
    }

    @Override
    PaymentClassification getClassification() {
        return new HourlyClassification(itsRate);
    }
}