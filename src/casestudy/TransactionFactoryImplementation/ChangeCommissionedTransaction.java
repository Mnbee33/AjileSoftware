package casestudy.TransactionFactoryImplementation;

import casestudy.Classifications.CommissionedClassification;
import casestudy.GeneralTransactions.ChangeClassificationTransaction;
import casestudy.PayrollDomain.PaymentClassification;
import casestudy.PayrollDomain.PaymentSchedule;
import casestudy.Schedules.BiweeklySchedule;

public class ChangeCommissionedTransaction extends ChangeClassificationTransaction {
    private double itsSalary;
    private double itsRate;

    public ChangeCommissionedTransaction(int empId, double salary, double rate) {
        super(empId);
        itsSalary = salary;
        itsRate = rate;
    }

    @Override
    protected PaymentSchedule getSchedule() {
        return new BiweeklySchedule();
    }

    @Override
    protected PaymentClassification getClassification() {
        return new CommissionedClassification(itsSalary, itsRate);
    }
}