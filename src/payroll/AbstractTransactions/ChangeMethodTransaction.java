package payroll.AbstractTransactions;

import payroll.PayrollDomain.Employee;
import payroll.PayrollDomain.PaymentMethod;
import payroll.PayrollFactory.PayrollFactory;

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
