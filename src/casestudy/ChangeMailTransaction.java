package casestudy;

public class ChangeMailTransaction extends ChangeMethodTransaction {
    private String itsAddress;

    public ChangeMailTransaction(int empId, String address) {
        super(empId);
        itsAddress = address;
    }

    @Override
    PaymentMethod getMethod() {
        return new MailMethod(itsAddress);
    }
}
