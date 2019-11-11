package payroll.AbstractTransactions;

import payroll.PayrollDatabase.GlobalDatabase;
import payroll.PayrollDomain.Employee;
import payroll.PayrollFactory.PayrollFactory;
import payroll.TransactionApplication.Transaction;

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
