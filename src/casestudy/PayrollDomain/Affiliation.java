package casestudy.PayrollDomain;

import java.time.LocalDate;

public interface Affiliation {
    double getServiceCharge(LocalDate date);

    double calculateDeductions(PayCheck pc);
}
