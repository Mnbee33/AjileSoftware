package casestudy.TransactionFactoryImplementation;

import casestudy.AbstractTransactions.ChangeEmployeeTransaction;
import casestudy.PayrollDomain.Employee;
import casestudy.PayrollFactory.PayrollFactory;

public class ChangeNameTransaction extends ChangeEmployeeTransaction {
    private String itsName;

    public ChangeNameTransaction(int empId, String name, PayrollFactory pf) {
        super(empId, pf);
        itsName = name;
    }

    @Override
    protected void change(Employee e) {
        e.setName(itsName);
    }
}
