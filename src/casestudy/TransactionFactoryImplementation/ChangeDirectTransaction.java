package casestudy.TransactionFactoryImplementation;

import casestudy.AbstractTransactions.ChangeMethodTransaction;
import casestudy.PayrollDomain.PaymentMethod;
import casestudy.PayrollFactory.PayrollFactory;

public class ChangeDirectTransaction extends ChangeMethodTransaction {
    private String itsBank;
    private long itsAccount;

    public ChangeDirectTransaction(int empId, String bank, long account, PayrollFactory pf) {
        super(empId, pf);
        itsBank = bank;
        itsAccount = account;
    }

    @Override
    public PaymentMethod getMethod() {
        return itsPayrollFactory.makeDirectMethod(itsBank, itsAccount);
    }
}
