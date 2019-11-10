package casestudy.Transactions;

import casestudy.Methods.DirectMethod;
import casestudy.Methods.PaymentMethod;

public class ChangeDirectTransaction extends ChangeMethodTransaction {
    private String itsBank;
    private long itsAccount;

    public ChangeDirectTransaction(int empId, String bank, long account) {
        super(empId);
        itsBank = bank;
        itsAccount = account;
    }

    @Override
    PaymentMethod getMethod() {
        return new DirectMethod(itsBank, itsAccount);
    }
}
