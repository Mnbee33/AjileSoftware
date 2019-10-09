package casestudy;

import java.util.Calendar;

public class NoAffiliation implements Affiliation {
    @Override
    public double getServiceCharge(Calendar date) {
        return 0;
    }

    @Override
    public double calculateDeductions(PayCheck pc) {
        return 0;
    }
}
