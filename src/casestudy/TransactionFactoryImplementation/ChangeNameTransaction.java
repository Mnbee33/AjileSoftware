package casestudy.TransactionFactoryImplementation;

import casestudy.GeneralTransactions.ChangeEmployeeTransaction;
import casestudy.PayrollDomain.Employee;

public class ChangeNameTransaction extends ChangeEmployeeTransaction {
    private String itsName;

    public ChangeNameTransaction(int empId, String name) {
        super(empId);
        itsName = name;
    }

    @Override
    protected void change(Employee e) {
        e.setName(itsName);
    }
}
