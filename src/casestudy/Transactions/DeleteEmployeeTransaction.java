package casestudy.Transactions;

import casestudy.PayrollDatabase.PayrollDatabase;

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
