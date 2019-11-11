package payroll.TransactionFactoryImplementation;

import payroll.AbstractTransactions.ChangeMethodTransaction;
import payroll.PayrollDomain.PaymentMethod;
import payroll.PayrollFactory.PayrollFactory;

public class ChangeHoldTransaction extends ChangeMethodTransaction {
    public ChangeHoldTransaction(int empId, PayrollFactory pf) {
        super(empId, pf);
    }

    @Override
    protected PaymentMethod getMethod() {
        return itsPayrollFactory.makeHoldMethod();
    }
}
