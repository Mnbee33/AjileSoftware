package casestudy.TransactionFactoryImplementation;

import casestudy.Classifications.HourlyClassification;
import casestudy.GeneralTransactions.ChangeClassificationTransaction;
import casestudy.PayrollDomain.PaymentClassification;
import casestudy.PayrollDomain.PaymentSchedule;
import casestudy.Schedules.WeeklySchedule;

public class ChangeHourlyTransaction extends ChangeClassificationTransaction {
    private double itsRate;

    public ChangeHourlyTransaction(int empId, double rate) {
        super(empId);
        itsRate = rate;
    }

    @Override
    protected PaymentSchedule getSchedule() {
        return new WeeklySchedule();
    }

    @Override
    protected PaymentClassification getClassification() {
        return new HourlyClassification(itsRate);
    }
}
