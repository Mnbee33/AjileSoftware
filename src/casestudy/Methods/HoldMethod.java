package casestudy.Methods;

import casestudy.Transactions.PayCheck;

public class HoldMethod implements PaymentMethod {
    @Override
    public void pay(PayCheck pc) {
        System.out.println("Hold Method");
    }
}
