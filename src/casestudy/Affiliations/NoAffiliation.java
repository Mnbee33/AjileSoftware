package casestudy.Affiliations;

import casestudy.Payment.PayCheck;
import casestudy.PayrollDomain.Affiliation;

import java.time.LocalDate;

public class NoAffiliation implements Affiliation {
    @Override
    public double getServiceCharge(LocalDate date) {
        return 0;
    }

    @Override
    public double calculateDeductions(PayCheck pc) {
        return 0;
    }
}
