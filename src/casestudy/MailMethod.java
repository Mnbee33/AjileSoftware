package casestudy;

public class MailMethod implements PaymentMethod {
    private String itsAddress;

    public MailMethod(String address) {
        itsAddress = address;
    }

    public String getAddress() {
        return itsAddress;
    }

    @Override
    public void pay(PayCheck pc) {

    }
}
