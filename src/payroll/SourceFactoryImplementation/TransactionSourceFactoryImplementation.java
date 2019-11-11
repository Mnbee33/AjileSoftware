package payroll.SourceFactoryImplementation;

import payroll.SourceFactory.TransactionSourceFactory;
import payroll.TransactionFactory.TransactionFactory;

public class TransactionSourceFactoryImplementation implements TransactionSourceFactory {
    public TextParserTransactionSource makeTextParserTransactionSource(String path, TransactionFactory tf) {
        return new TextParserTransactionSource(path, tf);
    }
}
