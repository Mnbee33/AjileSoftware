package casestudy.Transactions;

import casestudy.Classifications.PaymentClassification;
import casestudy.PayrollDatabase.Employee;
import casestudy.Schedules.PaymentSchedule;

public abstract class ChangeClassificationTransaction extends ChangeEmployeeTransaction {

    public ChangeClassificationTransaction(int empId) {
        super(empId);
    }

    @Override
    void change(Employee e) {
        e.setSchedule(getSchedule());
        e.setClassification(getClassification());
    }

    abstract PaymentSchedule getSchedule();

    abstract PaymentClassification getClassification();
}
