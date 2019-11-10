package casestudy.SourceFactoryImplementation;

import casestudy.SourceFactory.TransactionSourceFactory;
import casestudy.TransactionFactory.TransactionFactory;

public class TransactionSourceFactoryImplementation implements TransactionSourceFactory {
    public TextParserTransactionSource makeTextParserTransactionSource(String path, TransactionFactory tf) {
        return new TextParserTransactionSource(path, tf);
    }
}
