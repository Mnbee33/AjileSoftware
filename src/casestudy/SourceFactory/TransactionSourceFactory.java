package casestudy.SourceFactory;

import casestudy.SourceFactoryImplementation.TextParserTransactionSource;
import casestudy.TransactionFactory.TransactionFactory;

public interface TransactionSourceFactory {
    TextParserTransactionSource makeTextParserTransactionSource(String path, TransactionFactory tf);
}
