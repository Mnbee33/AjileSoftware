package casestudy.TransactionFactoryImplementation;

import casestudy.AbstractTransactions.AddEmployeeTransaction;
import casestudy.PayrollDomain.PaymentClassification;
import casestudy.PayrollDomain.PaymentSchedule;
import casestudy.PayrollFactory.PayrollFactory;

public class AddHourlyEmployee extends AddEmployeeTransaction {
    private double itsHourlyRate;

    public AddHourlyEmployee(int empId, String name, String address, double hourlyRate, PayrollFactory pf) {
        super(empId, name, address, pf);
        itsHourlyRate = hourlyRate;
    }

    @Override
    protected PaymentClassification getClassification() {
        return itsPayrollFactory.makeHourlyClassification(itsHourlyRate);
    }

    @Override
    protected PaymentSchedule getSchedule() {
        return itsPayrollFactory.makeWeeklySchedule();
    }
}
