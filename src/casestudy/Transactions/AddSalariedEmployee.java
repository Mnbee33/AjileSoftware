package casestudy.Transactions;

import casestudy.Classifications.PaymentClassification;
import casestudy.Classifications.SalariedClassification;
import casestudy.Schedules.MonthlySchedule;
import casestudy.Schedules.PaymentSchedule;

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
