package casestudy.TransactionFactoryImplementation;

import casestudy.PayrollDatabase.GlobalDatabase;
import casestudy.TransactionApplication.Transaction;

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
