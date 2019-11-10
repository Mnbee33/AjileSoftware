package casestudy.Affiliations;

import casestudy.Transactions.PayCheck;

import java.time.LocalDate;

public interface Affiliation {
    double getServiceCharge(LocalDate date);

    double calculateDeductions(PayCheck pc);
}
