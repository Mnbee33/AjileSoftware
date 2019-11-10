package casestudy.TransactionFactoryImplementation;

import casestudy.AbstractTransactions.ChangeClassificationTransaction;
import casestudy.PayrollDomain.PaymentClassification;
import casestudy.PayrollDomain.PaymentSchedule;
import casestudy.PayrollFactory.PayrollFactory;

public class ChangeSalariedTransaction extends ChangeClassificationTransaction {
    private double itsSalary;

    public ChangeSalariedTransaction(int empId, double salary, PayrollFactory pf) {
        super(empId, pf);
        itsSalary = salary;
    }

    @Override
    protected PaymentSchedule getSchedule() {
        return itsPayrollFactory.makeMonthlySchedule();
    }

    @Override
    protected PaymentClassification getClassification() {
        return itsPayrollFactory.makeSalariedClassification(itsSalary);
    }
}
