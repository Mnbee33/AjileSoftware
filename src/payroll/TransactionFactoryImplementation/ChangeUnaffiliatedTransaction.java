package payroll.TransactionFactoryImplementation;

import payroll.AbstractTransactions.ChangeAffiliationTransaction;
import payroll.PayrollDatabase.GlobalDatabase;
import payroll.PayrollDomain.Affiliation;
import payroll.PayrollDomain.Employee;
import payroll.PayrollFactory.PayrollFactory;
import payroll.PayrollFactoryImplementation.UnionAffiliation;

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
