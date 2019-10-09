package casestudy;

import java.util.Calendar;

public class ServiceChargeTransaction implements Transaction {
    private int itsMemberId;
    private Calendar itsDate;
    private double itsAmount;

    public ServiceChargeTransaction(int memberId, Calendar date, double amount) {
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
