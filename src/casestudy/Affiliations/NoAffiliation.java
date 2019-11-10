package casestudy.Affiliations;

import casestudy.Transactions.PayCheck;

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
