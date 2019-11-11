package payroll.PayrollFactoryImplementation;

import payroll.PayrollDomain.PayCheck;
import payroll.PayrollDomain.PaymentMethod;

public class HoldMethod implements PaymentMethod {
    @Override
    public void pay(PayCheck pc) {
        System.out.println("Hold Method");
    }
}
