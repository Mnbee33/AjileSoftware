package payroll.PayrollFactoryImplementation;

import payroll.PayrollDomain.*;
import payroll.PayrollFactory.PayrollFactory;

import java.time.LocalDate;

public class PayrollFactoryImplementation implements PayrollFactory {
    @Override
    public PaymentClassification makeCommissionedClassification(double salary, double commissionRate) {
        return new CommissionedClassification(salary, commissionRate);
    }

    @Override
    public PaymentMethod makeDirectMethod(String bank, long account) {
        return new DirectMethod(bank, account);
    }

    @Override
    public PaymentMethod makeHoldMethod() {
        return new HoldMethod();
    }

    @Override
    public PaymentClassification makeHourlyClassification(double hourlyRate) {
        return new HourlyClassification(hourlyRate);
    }

    @Override
    public PaymentMethod makeMailMethod(String address) {
        return new MailMethod(address);
    }

    @Override
    public PayCheck makePaycheck(LocalDate payPeriodStartDate, LocalDate payPeriodEndDate) {
        return new PaycheckImplementation(payPeriodStartDate, payPeriodEndDate);
    }

    @Override
    public PaymentClassification makeSalariedClassification(double salary) {
        return new SalariedClassification(salary);
    }

    @Override
    public PaymentSchedule makeMonthlySchedule() {
        return new MonthlySchedule();
    }

    @Override
    public PaymentSchedule makeBiweeklySchedule() {
        return new BiweeklySchedule();
    }

    @Override
    public PaymentSchedule makeWeeklySchedule() {
        return new WeeklySchedule();
    }

    @Override
    public Affiliation makeUnionAffiliation(int memberId, double dues) {
        return new UnionAffiliation(memberId, dues);
    }

    @Override
    public Affiliation makeNoAffiliation() {
        return new NoAffiliation();
    }
}
