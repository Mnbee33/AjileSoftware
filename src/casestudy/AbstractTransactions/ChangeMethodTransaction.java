package casestudy.AbstractTransactions;

import casestudy.PayrollDomain.Employee;
import casestudy.PayrollDomain.PaymentMethod;
import casestudy.PayrollFactory.PayrollFactory;

public abstract class ChangeMethodTransaction extends ChangeEmployeeTransaction {

    public ChangeMethodTransaction(int empId, PayrollFactory pf) {
        super(empId, pf);
    }

    @Override
    protected void change(Employee e) {
        e.setMethod(getMethod());
    }

    protected abstract PaymentMethod getMethod();
}
