package casestudy;

import java.util.HashMap;
import java.util.Map;

public class UnionAffiliation implements Affiliation {
    private double itsDues;
    private int itsMemberId;
    private Map<Long, Double> serviceCharges = new HashMap<>();

    public UnionAffiliation(int memberId, double dues) {
        itsMemberId = memberId;
        itsDues = dues;
    }

    @Override
    public double getServiceCharge(long date) {
        return serviceCharges.get(date);
    }

    @Override
    public double calculateDeductions(PayCheck pc) {
        return 0;
    }

    public void addServiceCharge(long date, double amount) {
        serviceCharges.put(date, amount);
    }

    public double getDues() {
        return itsDues;
    }

    public int getMemberId() {
        return itsMemberId;
    }
}
