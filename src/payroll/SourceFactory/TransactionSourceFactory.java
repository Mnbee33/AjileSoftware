package payroll.SourceFactory;

import payroll.SourceFactoryImplementation.TextParserTransactionSource;
import payroll.TransactionFactory.TransactionFactory;

public interface TransactionSourceFactory {
    TextParserTransactionSource makeTextParserTransactionSource(String path, TransactionFactory tf);
}
