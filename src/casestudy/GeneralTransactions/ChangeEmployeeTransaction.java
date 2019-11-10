package casestudy.GeneralTransactions;

import casestudy.PayrollDatabase.GlobalDatabase;
import casestudy.PayrollDomain.Employee;
import casestudy.TransactionApplication.Transaction;

public abstract class ChangeEmployeeTransaction implements Transaction {
    private int itsEmpId;

    public ChangeEmployeeTransaction(int empId) {
        itsEmpId = empId;
    }

    @Override
    public void execute() {
        Employee e = GlobalDatabase.database.getEmployee(itsEmpId);
        if (e != null) {
            change(e);
        }
    }

    protected abstract void change(Employee e);
}
