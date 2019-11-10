package casestudy.TransactionFactoryImplementation;

import casestudy.AbstractTransactions.ChangeClassificationTransaction;
import casestudy.PayrollDomain.PaymentClassification;
import casestudy.PayrollDomain.PaymentSchedule;
import casestudy.PayrollFactory.PayrollFactory;

public class ChangeCommissionedTransaction extends ChangeClassificationTransaction {
    private double itsSalary;
    private double itsRate;

    public ChangeCommissionedTransaction(int empId, double salary, double rate, PayrollFactory pf) {
        super(empId, pf);
        itsSalary = salary;
        itsRate = rate;
    }

    @Override
    protected PaymentSchedule getSchedule() {
        return itsPayrollFactory.makeBiweeklySchedule();
    }

    @Override
    protected PaymentClassification getClassification() {
        return itsPayrollFactory.makeCommissionedClassification(itsSalary, itsRate);
    }
}
