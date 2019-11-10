package casestudy.AbstractTransactions;

import casestudy.PayrollDomain.Employee;
import casestudy.PayrollDomain.PaymentClassification;
import casestudy.PayrollDomain.PaymentSchedule;
import casestudy.PayrollFactory.PayrollFactory;

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
