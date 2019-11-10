package casestudy.MethodTransactions;

import casestudy.Methods.HoldMethod;
import casestudy.PayrollDomain.PaymentMethod;

public class ChangeHoldTransaction extends ChangeMethodTransaction {
    public ChangeHoldTransaction(int empId) {
        super(empId);
    }

    @Override
    PaymentMethod getMethod() {
        return new HoldMethod();
    }
}
