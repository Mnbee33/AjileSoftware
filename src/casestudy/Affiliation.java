package casestudy;

import java.util.Calendar;

public interface Affiliation {
    double getServiceCharge(Calendar date);

    double calculateDeductions(PayCheck pc);
}
