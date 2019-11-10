package casestudy.TransactionFactoryImplementation;

import casestudy.AbstractTransactions.ChangeClassificationTransaction;
import casestudy.PayrollDomain.PaymentClassification;
import casestudy.PayrollDomain.PaymentSchedule;
import casestudy.PayrollFactory.PayrollFactory;

public class ChangeHourlyTransaction extends ChangeClassificationTransaction {
    private double itsRate;

    public ChangeHourlyTransaction(int empId, double rate, PayrollFactory pf) {
        super(empId, pf);
        itsRate = rate;
    }

    @Override
    protected PaymentSchedule getSchedule() {
        return itsPayrollFactory.makeWeeklySchedule();
    }

    @Override
    protected PaymentClassification getClassification() {
        return itsPayrollFactory.makeHourlyClassification(itsRate);
    }
}
