package casestudy.PayrollApplication;

import casestudy.Transactions.Transaction;

import java.util.List;

public interface TransactionSource {
    List<Transaction> getTransactions();
}