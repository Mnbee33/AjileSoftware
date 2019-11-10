package casestudy.Transactions;

import casestudy.Affiliations.Affiliation;
import casestudy.Affiliations.UnionAffiliation;
import casestudy.PayrollDatabase.Employee;
import casestudy.PayrollDatabase.PayrollDatabase;

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
        PayrollDatabase.addUnionMember(itsMemberId, e);
    }

    @Override
    Affiliation getAffiliation() {
        return new UnionAffiliation(itsMemberId, itsDues);
    }
}