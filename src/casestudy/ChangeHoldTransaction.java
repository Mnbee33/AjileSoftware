package casestudy;

public class ChangeHoldTransaction extends ChangeMethodTransaction {
    public ChangeHoldTransaction(int empId) {
        super(empId);
    }

    @Override
    PaymentMethod getMethod() {
        return new HoldMethod();
    }
}
