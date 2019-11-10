package casestudy.Transactions;

import casestudy.Methods.MailMethod;
import casestudy.Methods.PaymentMethod;

public class ChangeMailTransaction extends ChangeMethodTransaction {
    private String itsAddress;

    public ChangeMailTransaction(int empId, String address) {
        super(empId);
        itsAddress = address;
    }

    @Override
    PaymentMethod getMethod() {
        return new MailMethod(itsAddress);
    }
}
