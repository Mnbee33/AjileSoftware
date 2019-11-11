package payroll.TransactionFactoryImplementation;

import payroll.AbstractTransactions.ChangeClassificationTransaction;
import payroll.PayrollDomain.PaymentClassification;
import payroll.PayrollDomain.PaymentSchedule;
import payroll.PayrollFactory.PayrollFactory;

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
