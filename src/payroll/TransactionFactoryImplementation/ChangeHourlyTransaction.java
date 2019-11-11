package payroll.TransactionFactoryImplementation;

import payroll.AbstractTransactions.ChangeClassificationTransaction;
import payroll.PayrollDomain.PaymentClassification;
import payroll.PayrollDomain.PaymentSchedule;
import payroll.PayrollFactory.PayrollFactory;

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
