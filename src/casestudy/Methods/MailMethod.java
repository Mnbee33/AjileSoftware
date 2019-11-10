package casestudy.Methods;

import casestudy.PayrollDomain.PayCheck;
import casestudy.PayrollDomain.PaymentMethod;

public class MailMethod implements PaymentMethod {
    private String itsAddress;

    public MailMethod(String address) {
        itsAddress = address;
    }

    public String getAddress() {
        return itsAddress;
    }

    @Override
    public void pay(PayCheck pc) {
        System.out.println("Mail Method");
    }
}
