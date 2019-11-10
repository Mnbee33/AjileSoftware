package casestudy.PayrollApplication;

import casestudy.Transactions.Transaction;

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
