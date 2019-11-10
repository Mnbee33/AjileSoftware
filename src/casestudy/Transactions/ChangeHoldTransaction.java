package casestudy.Transactions;

import casestudy.Methods.HoldMethod;
import casestudy.Methods.PaymentMethod;

public class ChangeHoldTransaction extends ChangeMethodTransaction {
    public ChangeHoldTransaction(int empId) {
        super(empId);
    }

    @Override
    PaymentMethod getMethod() {
        return new HoldMethod();
    }
}
