package casestudy;

import java.util.List;

public class PayrollApplication {
    private TransactionSource source;
    private List<Transaction> transactions;

    void setSource(TransactionSource source) {
        this.source = source;
    }

    public void execute() {
        transactions = source.getTransactions();
        for (Transaction t : transactions) {
            t.execute();
        }
    }
}
