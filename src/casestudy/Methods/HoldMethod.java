package casestudy.Methods;

import casestudy.Payment.PayCheck;
import casestudy.PayrollDomain.PaymentMethod;

public class HoldMethod implements PaymentMethod {
    @Override
    public void pay(PayCheck pc) {
        System.out.println("Hold Method");
    }
}
