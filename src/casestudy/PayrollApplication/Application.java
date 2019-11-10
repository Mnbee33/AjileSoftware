package casestudy.PayrollApplication;

public interface Application {
    void setSource(TransactionSource source);

    void execute();
}
