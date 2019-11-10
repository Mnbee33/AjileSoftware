package casestudy.GeneralTransactions;

import casestudy.PayrollDatabase.PayrollDatabase;
import casestudy.TransactionApplication.Transaction;

public class DeleteEmployeeTransaction implements Transaction {
    private int itsEmpId;

    public DeleteEmployeeTransaction(int empId) {
        itsEmpId = empId;
    }

    @Override
    public void execute() {
        PayrollDatabase.deleteEmployee(itsEmpId);
    }
}
