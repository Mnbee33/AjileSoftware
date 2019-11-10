package casestudy.TransactionFactoryImplementation;

import casestudy.GeneralTransactions.ChangeMethodTransaction;
import casestudy.Methods.MailMethod;
import casestudy.PayrollDomain.PaymentMethod;

public class ChangeMailTransaction extends ChangeMethodTransaction {
    private String itsAddress;

    public ChangeMailTransaction(int empId, String address) {
        super(empId);
        itsAddress = address;
    }

    @Override
    protected PaymentMethod getMethod() {
        return new MailMethod(itsAddress);
    }
}
