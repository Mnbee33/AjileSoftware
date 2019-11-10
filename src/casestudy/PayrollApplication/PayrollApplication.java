package casestudy.PayrollApplication;

import casestudy.TransactionApplication.Application;
import casestudy.TransactionApplication.Transaction;
import casestudy.TransactionApplication.TransactionSource;

import java.util.List;

public class PayrollApplication implements Application {
    private TransactionSource source;
    private List<Transaction> transactions;

    @Override
    public void setSource(TransactionSource source) {
        this.source = source;
    }

    @Override
    public void execute() {
        transactions = source.getTransactions();
        for (Transaction t : transactions) {
            t.execute();
        }
    }
}
