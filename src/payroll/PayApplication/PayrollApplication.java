package payroll.PayApplication;

import payroll.PayrollFactory.PayrollFactory;
import payroll.PayrollFactoryImplementation.PayrollFactoryImplementation;
import payroll.SourceFactory.TransactionSourceFactory;
import payroll.SourceFactoryImplementation.TransactionSourceFactoryImplementation;
import payroll.TransactionApplication.Application;
import payroll.TransactionApplication.Transaction;
import payroll.TransactionApplication.TransactionSource;
import payroll.TransactionFactory.TransactionFactory;
import payroll.TransactionFactoryImplementation.TransactionFactoryImplementation;

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
