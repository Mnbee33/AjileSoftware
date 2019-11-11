package payroll.TransactionFactoryImplementation;

import payroll.AbstractTransactions.AddEmployeeTransaction;
import payroll.PayrollDomain.PaymentClassification;
import payroll.PayrollDomain.PaymentSchedule;
import payroll.PayrollFactory.PayrollFactory;

public class AddCommissionedEmployee extends AddEmployeeTransaction {
    private double itsSalary;
    private double itsCommissionRate;

    public AddCommissionedEmployee(int empId, String name, String address, double salary, double commissionRate, PayrollFactory pf) {
        super(empId, name, address, pf);
        itsSalary = salary;
        itsCommissionRate = commissionRate;
    }

    @Override
    protected PaymentClassification getClassification() {
        return itsPayrollFactory.makeCommissionedClassification(itsSalary, itsCommissionRate);
    }

    @Override
    protected PaymentSchedule getSchedule() {
        return itsPayrollFactory.makeBiweeklySchedule();
    }
}
