package casestudy.TransactionFactoryImplementation;

import casestudy.Classifications.SalariedClassification;
import casestudy.GeneralTransactions.ChangeClassificationTransaction;
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
    protected PaymentSchedule getSchedule() {
        return new MonthlySchedule();
    }

    @Override
    protected PaymentClassification getClassification() {
        return new SalariedClassification(itsSalary);
    }
}
