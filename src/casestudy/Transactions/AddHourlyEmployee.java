package casestudy.Transactions;

import casestudy.Classifications.HourlyClassification;
import casestudy.Classifications.PaymentClassification;
import casestudy.Schedules.PaymentSchedule;
import casestudy.Schedules.WeeklySchedule;

public class AddHourlyEmployee extends AddEmployeeTransaction {
    private double itsHourlyRate;

    public AddHourlyEmployee(int empId, String name, String address, double hourlyRate) {
        super(empId, name, address);
        itsHourlyRate = hourlyRate;
    }

    @Override
    protected PaymentClassification getClassification() {
        return new HourlyClassification(itsHourlyRate);
    }

    @Override
    protected PaymentSchedule getSchedule() {
        return new WeeklySchedule();
    }
}
