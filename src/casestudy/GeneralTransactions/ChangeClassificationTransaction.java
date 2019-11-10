package casestudy.GeneralTransactions;

import casestudy.PayrollDomain.Employee;
import casestudy.PayrollDomain.PaymentClassification;
import casestudy.PayrollDomain.PaymentSchedule;

public abstract class ChangeClassificationTransaction extends ChangeEmployeeTransaction {

    public ChangeClassificationTransaction(int empId) {
        super(empId);
    }

    @Override
    public void change(Employee e) {
        e.setSchedule(getSchedule());
        e.setClassification(getClassification());
    }

    protected abstract PaymentSchedule getSchedule();

    protected abstract PaymentClassification getClassification();
}
