package casestudy.TransactionFactoryImplementation;

import casestudy.AbstractTransactions.ChangeEmployeeTransaction;
import casestudy.PayrollDomain.Employee;
import casestudy.PayrollFactory.PayrollFactory;

public class ChangeAddressTransaction extends ChangeEmployeeTransaction {
    private String itsAddress;

    public ChangeAddressTransaction(int empId, String address, PayrollFactory pf) {
        super(empId, pf);
        itsAddress = address;
    }

    @Override
    protected void change(Employee e) {
        e.setAddress(itsAddress);
    }
}
