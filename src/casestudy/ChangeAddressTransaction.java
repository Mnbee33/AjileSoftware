package casestudy;

public class ChangeAddressTransaction extends ChangeEmployeeTransaction {
    private String itsAddress;

    public ChangeAddressTransaction(int empId, String address) {
        super(empId);
        itsAddress = address;
    }

    @Override
    void change(Employee e) {
        e.setAddress(itsAddress);
    }
}
