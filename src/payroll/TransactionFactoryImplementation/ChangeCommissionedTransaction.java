package payroll.TransactionFactoryImplementation;

import payroll.AbstractTransactions.ChangeClassificationTransaction;
import payroll.PayrollDomain.PaymentClassification;
import payroll.PayrollDomain.PaymentSchedule;
import payroll.PayrollFactory.PayrollFactory;

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
