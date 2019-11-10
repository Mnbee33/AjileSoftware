package casestudy.PayrollDomain;

import casestudy.Payment.PayCheck;

public interface PaymentMethod {
    void pay(PayCheck pc);
}
