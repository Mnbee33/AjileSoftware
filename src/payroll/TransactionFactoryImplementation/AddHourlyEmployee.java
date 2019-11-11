package payroll.TransactionFactoryImplementation;

import payroll.AbstractTransactions.AddEmployeeTransaction;
import payroll.PayrollDomain.PaymentClassification;
import payroll.PayrollDomain.PaymentSchedule;
import payroll.PayrollFactory.PayrollFactory;

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
