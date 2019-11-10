package casestudy.TransactionFactoryImplementation;

import casestudy.GeneralTransactions.ChangeMethodTransaction;
import casestudy.Methods.DirectMethod;
import casestudy.PayrollDomain.PaymentMethod;

public class ChangeDirectTransaction extends ChangeMethodTransaction {
    private String itsBank;
    private long itsAccount;

    public ChangeDirectTransaction(int empId, String bank, long account) {
        super(empId);
        itsBank = bank;
        itsAccount = account;
    }

    @Override
    public PaymentMethod getMethod() {
        return new DirectMethod(itsBank, itsAccount);
    }
}
