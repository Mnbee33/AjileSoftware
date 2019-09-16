package casestudy;

import java.util.HashMap;
import java.util.Map;

public class UnionAffiliation implements Affiliation {
    private double itsDues;
    private Map<Long, Double> serviceCharges = new HashMap<>();

    public UnionAffiliation(double dues) {
        itsDues = dues;
    }

    @Override
    public double getServiceCharge(long date) {
        return serviceCharges.get(date);
    }

    public void addServiceCharge(long date, double amount) {
        serviceCharges.put(date, amount);
    }
}
