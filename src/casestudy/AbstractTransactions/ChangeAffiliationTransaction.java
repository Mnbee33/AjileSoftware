package casestudy.AbstractTransactions;

import casestudy.PayrollDomain.Affiliation;
import casestudy.PayrollDomain.Employee;
import casestudy.PayrollFactory.PayrollFactory;

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
