package payroll.TransactionFactoryImplementation;

import payroll.AbstractTransactions.ChangeEmployeeTransaction;
import payroll.PayrollDomain.Employee;
import payroll.PayrollFactory.PayrollFactory;

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
