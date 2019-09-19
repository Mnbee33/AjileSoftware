package casestudy;

public abstract class ChangeMethodTransaction extends ChangeEmployeeTransaction {

    public ChangeMethodTransaction(int empId) {
        super(empId);
    }

    @Override
    void change(Employee e) {
        e.setMethod(getMethod());
    }

    abstract PaymentMethod getMethod();
}
