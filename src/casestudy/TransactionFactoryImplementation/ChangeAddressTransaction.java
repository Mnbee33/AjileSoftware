package casestudy.TransactionFactoryImplementation;

import casestudy.GeneralTransactions.ChangeEmployeeTransaction;
import casestudy.PayrollDomain.Employee;

public class ChangeAddressTransaction extends ChangeEmployeeTransaction {
    private String itsAddress;

    public ChangeAddressTransaction(int empId, String address) {
        super(empId);
        itsAddress = address;
    }

    @Override
    protected void change(Employee e) {
        e.setAddress(itsAddress);
    }
}