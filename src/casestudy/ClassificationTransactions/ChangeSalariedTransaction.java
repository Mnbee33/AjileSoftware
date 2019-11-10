package casestudy.ClassificationTransactions;

import casestudy.Classifications.SalariedClassification;
import casestudy.PayrollDomain.PaymentClassification;
import casestudy.PayrollDomain.PaymentSchedule;
import casestudy.Schedules.MonthlySchedule;

public class ChangeSalariedTransaction extends ChangeClassificationTransaction {
    private double itsSalary;

    public ChangeSalariedTransaction(int empId, double salary) {
        super(empId);
        itsSalary = salary;
    }

    @Override
    PaymentSchedule getSchedule() {
        return new MonthlySchedule();
    }

    @Override
    PaymentClassification getClassification() {
        return new SalariedClassification(itsSalary);
    }
}
