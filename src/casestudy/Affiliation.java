package casestudy;

public interface Affiliation {
    double getServiceCharge(long date);

    double calculateDeductions(PayCheck pc);
}
