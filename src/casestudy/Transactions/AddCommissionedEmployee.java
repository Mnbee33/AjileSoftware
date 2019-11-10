package casestudy.Transactions;

import casestudy.Classifications.CommissionedClassification;
import casestudy.Classifications.PaymentClassification;
import casestudy.Schedules.BiweeklySchedule;
import casestudy.Schedules.PaymentSchedule;

public class AddCommissionedEmployee extends AddEmployeeTransaction {
    private double itsSalary;
    private double itsCommissionRate;

    public AddCommissionedEmployee(int empId, String name, String address, double salary, double commissionRate) {
        super(empId, name, address);
        itsSalary = salary;
        itsCommissionRate = commissionRate;
    }

    @Override
    protected PaymentClassification getClassification() {
        return new CommissionedClassification(itsSalary, itsCommissionRate);
    }

    @Override
    protected PaymentSchedule getSchedule() {
        return new BiweeklySchedule();
    }
}
