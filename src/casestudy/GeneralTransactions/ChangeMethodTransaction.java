package casestudy.GeneralTransactions;

import casestudy.PayrollDomain.Employee;
import casestudy.PayrollDomain.PaymentMethod;

public abstract class ChangeMethodTransaction extends ChangeEmployeeTransaction {

    public ChangeMethodTransaction(int empId) {
        super(empId);
    }

    @Override
    protected void change(Employee e) {
        e.setMethod(getMethod());
    }

    protected abstract PaymentMethod getMethod();
}