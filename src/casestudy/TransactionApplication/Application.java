package casestudy.TransactionApplication;

public interface Application {
    void setSource(TransactionSource source);

    void execute();
}
