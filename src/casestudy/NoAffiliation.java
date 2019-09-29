package casestudy;

public class NoAffiliation implements Affiliation {
    @Override
    public double getServiceCharge(long date) {
        return 0;
    }

    @Override
    public double calculateDeductions(PayCheck pc) {
        return 0;
    }
}
