package payroll.AbstractTransactions;

import payroll.PayrollDomain.Employee;
import payroll.PayrollDomain.PaymentClassification;
import payroll.PayrollDomain.PaymentSchedule;
import payroll.PayrollFactory.PayrollFactory;

public abstract class ChangeClassificationTransaction extends ChangeEmployeeTransaction {
    public ChangeClassificationTransaction(int empId, PayrollFactory pf) {
        super(empId, pf);
    }

    @Override
    public void change(Employee e) {
        e.setSchedule(getSchedule());
        e.setClassification(getClassification());
    }

    protected abstract PaymentSchedule getSchedule();

    protected abstract PaymentClassification getClassification();
}
