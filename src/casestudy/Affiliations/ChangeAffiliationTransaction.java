package casestudy.Affiliations;

import casestudy.GeneralTransactions.ChangeEmployeeTransaction;
import casestudy.PayrollDomain.Affiliation;
import casestudy.PayrollDomain.Employee;

public abstract class ChangeAffiliationTransaction extends ChangeEmployeeTransaction {
    public ChangeAffiliationTransaction(int empId) {
        super(empId);
    }

    @Override
    protected void change(Employee e) {
        recordMembership(e);
        e.setAffiliation(getAffiliation());
    }

    abstract void recordMembership(Employee e);

    abstract Affiliation getAffiliation();
}
