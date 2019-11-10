package casestudy.PayrollDomain;

import casestudy.Payment.PayCheck;

import java.time.LocalDate;

public interface Affiliation {
    double getServiceCharge(LocalDate date);

    double calculateDeductions(PayCheck pc);
}
