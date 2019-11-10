package casestudy.Affiliations;

import casestudy.PayrollDatabase.PayrollDatabase;
import casestudy.PayrollDomain.Affiliation;
import casestudy.PayrollDomain.Employee;
import casestudy.PayrollDomain.Transaction;

import java.time.LocalDate;

public class ServiceChargeTransaction implements Transaction {
    private int itsMemberId;
    private LocalDate itsDate;
    private double itsAmount;

    public ServiceChargeTransaction(int memberId, LocalDate date, double amount) {
        itsMemberId = memberId;
        itsDate = date;
        itsAmount = amount;
    }

    @Override
    public void execute() {
        Employee e = PayrollDatabase.getUnionMember(itsMemberId);
        Affiliation af = e.getAffiliation();
        if (af instanceof UnionAffiliation) {
            UnionAffiliation uaf = (UnionAffiliation) af;
            uaf.addServiceCharge(itsDate, itsAmount);
        }
    }
}
