package casestudy.ClassificationTransactions;

import casestudy.Classifications.SalariedClassification;
import casestudy.GeneralTransactions.AddEmployeeTransaction;
import casestudy.PayrollDomain.PaymentClassification;
import casestudy.PayrollDomain.PaymentSchedule;
import casestudy.Schedules.MonthlySchedule;

public class AddSalariedEmployee extends AddEmployeeTransaction {
    private double itsSalary;

    public AddSalariedEmployee(int empId, String name, String address, double salary) {
        super(empId, name, address);
        itsSalary = salary;
    }

    @Override
    protected PaymentClassification getClassification() {
        return new SalariedClassification(itsSalary);
    }

    @Override
    protected PaymentSchedule getSchedule() {
        return new MonthlySchedule();
    }
}
