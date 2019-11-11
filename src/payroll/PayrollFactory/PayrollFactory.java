package payroll.PayrollFactory;

import payroll.PayrollDomain.*;

import java.time.LocalDate;

public interface PayrollFactory {
    PaymentClassification makeCommissionedClassification(double salary, double commissionRate);

    PaymentMethod makeDirectMethod(String bank, long account);

    PaymentMethod makeHoldMethod();

    PaymentClassification makeHourlyClassification(double hourlyRate);

    PaymentMethod makeMailMethod(String address);

    PayCheck makePaycheck(LocalDate payPeriodStartDate, LocalDate payPeriodEndDate);

    PaymentClassification makeSalariedClassification(double salary);

    PaymentSchedule makeBiweeklySchedule();

    PaymentSchedule makeWeeklySchedule();

    PaymentSchedule makeMonthlySchedule();

    Affiliation makeUnionAffiliation(int memberId, double dues);

    Affiliation makeNoAffiliation();
}
