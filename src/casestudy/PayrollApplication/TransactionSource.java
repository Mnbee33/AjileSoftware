package casestudy.PayrollApplication;

import casestudy.PayrollDomain.Transaction;

import java.util.List;

public interface TransactionSource {
    List<Transaction> getTransactions();
}
