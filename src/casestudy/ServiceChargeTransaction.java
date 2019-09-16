package casestudy;

public class ServiceChargeTransaction implements Transaction {
    private int itsMemberId;
    private long itsDate;
    private double itsAmount;

    public ServiceChargeTransaction(int memberId, long date, double amount) {
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
