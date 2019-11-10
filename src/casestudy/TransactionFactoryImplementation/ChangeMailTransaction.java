package casestudy.TransactionFactoryImplementation;

import casestudy.AbstractTransactions.ChangeMethodTransaction;
import casestudy.PayrollDomain.PaymentMethod;
import casestudy.PayrollFactory.PayrollFactory;

public class ChangeMailTransaction extends ChangeMethodTransaction {
    private String itsAddress;

    public ChangeMailTransaction(int empId, String address, PayrollFactory pf) {
        super(empId, pf);
        itsAddress = address;
    }

    @Override
    protected PaymentMethod getMethod() {
        return itsPayrollFactory.makeMailMethod(itsAddress);
    }
}
