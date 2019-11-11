package payroll.AbstractTransactions;

import payroll.PayrollDomain.Affiliation;
import payroll.PayrollDomain.Employee;
import payroll.PayrollFactory.PayrollFactory;

public abstract class ChangeAffiliationTransaction extends ChangeEmployeeTransaction {
    public ChangeAffiliationTransaction(int empId, PayrollFactory pf) {
        super(empId, pf);
    }

    @Override
    protected void change(Employee e) {
        recordMembership(e);
        e.setAffiliation(getAffiliation());
    }

    protected abstract void recordMembership(Employee e);

    protected abstract Affiliation getAffiliation();
}
