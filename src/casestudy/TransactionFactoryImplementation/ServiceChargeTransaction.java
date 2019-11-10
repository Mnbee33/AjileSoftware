package casestudy.TransactionFactoryImplementation;

import casestudy.Affiliations.UnionAffiliation;
import casestudy.PayrollDatabase.GlobalDatabase;
import casestudy.PayrollDomain.Affiliation;
import casestudy.PayrollDomain.Employee;
import casestudy.TransactionApplication.Transaction;

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
        Employee e = GlobalDatabase.database.getUnionMember(itsMemberId);
        Affiliation af = e.getAffiliation();
        if (af instanceof UnionAffiliation) {
            UnionAffiliation uaf = (UnionAffiliation) af;
            uaf.addServiceCharge(itsDate, itsAmount);
        }
    }
}
