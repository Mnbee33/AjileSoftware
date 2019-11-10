package casestudy.TransactionFactoryImplementation;

import casestudy.AbstractTransactions.ChangeMethodTransaction;
import casestudy.PayrollDomain.PaymentMethod;
import casestudy.PayrollFactory.PayrollFactory;

public class ChangeHoldTransaction extends ChangeMethodTransaction {
    public ChangeHoldTransaction(int empId, PayrollFactory pf) {
        super(empId, pf);
    }

    @Override
    protected PaymentMethod getMethod() {
        return itsPayrollFactory.makeHoldMethod();
    }
}
