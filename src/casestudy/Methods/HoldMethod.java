package casestudy.Methods;

import casestudy.PayrollDomain.PayCheck;
import casestudy.PayrollDomain.PaymentMethod;

public class HoldMethod implements PaymentMethod {
    @Override
    public void pay(PayCheck pc) {
        System.out.println("Hold Method");
    }
}
