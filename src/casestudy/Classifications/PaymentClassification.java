package casestudy.Classifications;

import casestudy.Transactions.PayCheck;

public interface PaymentClassification {
    double calculatePay(PayCheck pc);
}
