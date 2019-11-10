package casestudy.Methods;

import casestudy.PayrollDatabase.PayrollDatabase;
import casestudy.PayrollDomain.Employee;
import casestudy.PayrollDomain.Transaction;

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

    protected abstract void change(Employee e);
}
