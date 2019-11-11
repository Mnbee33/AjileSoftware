package payroll.TransactionApplication;

import java.util.List;

public interface TransactionSource {
    List<Transaction> getTransactions();
}
