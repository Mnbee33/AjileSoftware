package casestudy.Transactions;

import casestudy.PayrollDatabase.Employee;
import casestudy.PayrollDatabase.PayrollDatabase;

public abstract class ChangeEmployeeTransaction implements Transaction {
    private int itsEmpId;

    public ChangeEmployeeTransaction(int empId) {
        itsEmpId = empId;
    }

    @Override
    public void execute() {
        Employee e = PayrollDatabase.getEmployee(itsEmpId);
        if (e != null) {
            change(e);
        }
    }

    abstract void change(Employee e);
}
