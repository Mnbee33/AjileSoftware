package casestudy.TransactionFactoryImplementation;

import casestudy.GeneralTransactions.ChangeMethodTransaction;
import casestudy.Methods.HoldMethod;
import casestudy.PayrollDomain.PaymentMethod;

public class ChangeHoldTransaction extends ChangeMethodTransaction {
    public ChangeHoldTransaction(int empId) {
        super(empId);
    }

    @Override
    protected PaymentMethod getMethod() {
        return new HoldMethod();
    }
}
