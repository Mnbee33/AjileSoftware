package casestudy.PayApplication;

import casestudy.PayrollFactory.PayrollFactory;
import casestudy.PayrollFactoryImplementation.PayrollFactoryImplementation;
import casestudy.SourceFactory.TransactionSourceFactory;
import casestudy.SourceFactoryImplementation.TransactionSourceFactoryImplementation;
import casestudy.TransactionApplication.Application;
import casestudy.TransactionApplication.Transaction;
import casestudy.TransactionApplication.TransactionSource;
import casestudy.TransactionFactory.TransactionFactory;
import casestudy.TransactionFactoryImplementation.TransactionFactoryImplementation;

import java.util.List;

public class PayrollApplication implements Application {
    private TransactionSourceFactory tsf;
    private TransactionFactory tf;

    private TransactionSource source;
    private List<Transaction> transactions;

    public PayrollApplication() {
        tsf = new TransactionSourceFactoryImplementation();
        PayrollFactory pf = new PayrollFactoryImplementation();
        tf = new TransactionFactoryImplementation(pf);
    }

    @Override
    public void setSource(String path) {
        source = tsf.makeTextParserTransactionSource(path, tf);
    }

    @Override
    public void execute() {
        transactions = source.getTransactions();
        for (Transaction t : transactions) {
            t.execute();
        }
    }
}
