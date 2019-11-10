package casestudy.AffiliationTransactions;

import casestudy.Affiliations.UnionAffiliation;
import casestudy.PayrollDatabase.GlobalDatabase;
import casestudy.PayrollDomain.Affiliation;
import casestudy.PayrollDomain.Employee;

public class ChangeMemberTransaction extends ChangeAffiliationTransaction {
    private int itsMemberId;
    private double itsDues;

    public ChangeMemberTransaction(int empId, int memberId, double dues) {
        super(empId);
        itsMemberId = memberId;
        itsDues = dues;
    }

    @Override
    void recordMembership(Employee e) {
        GlobalDatabase.database.addUnionMember(itsMemberId, e);
    }

    @Override
    Affiliation getAffiliation() {
        return new UnionAffiliation(itsMemberId, itsDues);
    }
}
