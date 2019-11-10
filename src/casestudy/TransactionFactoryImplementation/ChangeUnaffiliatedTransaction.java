package casestudy.TransactionFactoryImplementation;

import casestudy.Affiliations.UnionAffiliation;
import casestudy.GeneralTransactions.ChangeAffiliationTransaction;
import casestudy.PayrollDatabase.GlobalDatabase;
import casestudy.PayrollDomain.Affiliation;
import casestudy.PayrollDomain.Employee;

public class ChangeUnaffiliatedTransaction extends ChangeAffiliationTransaction {
    public ChangeUnaffiliatedTransaction(int empId) {
        super(empId);
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
        return null;
    }
}
