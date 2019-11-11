package payroll.TransactionFactoryImplementation;

import payroll.AbstractTransactions.ChangeAffiliationTransaction;
import payroll.PayrollDatabase.GlobalDatabase;
import payroll.PayrollDomain.Affiliation;
import payroll.PayrollDomain.Employee;
import payroll.PayrollFactory.PayrollFactory;

public class ChangeMemberTransaction extends ChangeAffiliationTransaction {
    private int itsMemberId;
    private double itsDues;

    public ChangeMemberTransaction(int empId, int memberId, double dues, PayrollFactory pf) {
        super(empId, pf);
        itsMemberId = memberId;
        itsDues = dues;
    }

    @Override
    protected void recordMembership(Employee e) {
        GlobalDatabase.database.addUnionMember(itsMemberId, e);
    }

    @Override
    protected Affiliation getAffiliation() {
        return itsPayrollFactory.makeUnionAffiliation(itsMemberId, itsDues);
    }
}
