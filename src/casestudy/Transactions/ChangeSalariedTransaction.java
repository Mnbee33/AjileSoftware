package casestudy.Transactions;

import casestudy.Classifications.PaymentClassification;
import casestudy.Classifications.SalariedClassification;
import casestudy.Schedules.MonthlySchedule;
import casestudy.Schedules.PaymentSchedule;

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