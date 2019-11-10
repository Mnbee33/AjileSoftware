package casestudy.Transactions;

import casestudy.Affiliations.Affiliation;
import casestudy.Affiliations.UnionAffiliation;
import casestudy.PayrollDatabase.Employee;
import casestudy.PayrollDatabase.PayrollDatabase;

public class ChangeUnaffiliatedTransaction extends ChangeAffiliationTransaction {
    public ChangeUnaffiliatedTransaction(int empId) {
        super(empId);
    }

    @Override
    void recordMembership(Employee e) {
        Affiliation af = e.getAffiliation();
        if (af instanceof UnionAffiliation) {
            UnionAffiliation uf = (UnionAffiliation) af;
            int memberId = uf.getMemberId();
            PayrollDatabase.removeUnionMember(memberId);
        }
    }

    @Override
    Affiliation getAffiliation() {
        return null;
    }
}
