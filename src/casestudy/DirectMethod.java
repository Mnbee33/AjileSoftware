package casestudy;

public class DirectMethod implements PaymentMethod {
    private String itsBank;
    private long itsAccount;

    public DirectMethod(String bank, long account) {
        itsBank = bank;
        itsAccount = account;
    }

    public String getBank() {
        return itsBank;
    }

    public long getAccount() {
        return itsAccount;
    }
}
