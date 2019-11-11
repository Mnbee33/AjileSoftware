package payroll.TransactionFactoryImplementation;

import payroll.PayrollDatabase.GlobalDatabase;
import payroll.TransactionApplication.Transaction;

public class DeleteEmployeeTransaction implements Transaction {
    private int itsEmpId;

    public DeleteEmployeeTransaction(int empId) {
        itsEmpId = empId;
    }

    @Override
    public void execute() {
        GlobalDatabase.database.deleteEmployee(itsEmpId);
    }
}
