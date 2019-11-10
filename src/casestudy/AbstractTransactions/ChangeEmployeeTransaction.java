package casestudy.AbstractTransactions;

import casestudy.PayrollDatabase.GlobalDatabase;
import casestudy.PayrollDomain.Employee;
import casestudy.PayrollFactory.PayrollFactory;
import casestudy.TransactionApplication.Transaction;

public abstract class ChangeEmployeeTransaction implements Transaction {
    private int itsEmpId;
    protected PayrollFactory itsPayrollFactory;

    public ChangeEmployeeTransaction(int empId, PayrollFactory pf) {
        itsEmpId = empId;
        itsPayrollFactory = pf;
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
