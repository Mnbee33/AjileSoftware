package casestudy.Methods;

import casestudy.Transactions.PayCheck;

public interface PaymentMethod {
    void pay(PayCheck pc);
}
