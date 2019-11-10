package casestudy.TransactionFactoryImplementation;

import casestudy.AbstractTransactions.ChangeAffiliationTransaction;
import casestudy.PayrollDatabase.GlobalDatabase;
import casestudy.PayrollDomain.Affiliation;
import casestudy.PayrollDomain.Employee;
import casestudy.PayrollFactory.PayrollFactory;
import casestudy.PayrollFactoryImplementation.UnionAffiliation;

public class ChangeUnaffiliatedTransaction extends ChangeAffiliationTransaction {
    public ChangeUnaffiliatedTransaction(int empId, PayrollFactory pf) {
        super(empId, pf);
    }

    @Override
    protected void recordMembership(Employee e) {
        Affiliation af = e.getAffiliation();
        if (af instanceof UnionAffiliation) {
            UnionAffiliation uf = (UnionAffiliation) af;
            int memberId = uf.getMemberId();
            GlobalDatabase.database.removeUnionMember(memberId);
        }
    }

    @Override
    protected Affiliation getAffiliation() {
        return itsPayrollFactory.makeNoAffiliation();
    }
}
