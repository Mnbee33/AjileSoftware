package casestudy.TransactionFactoryImplementation;

import casestudy.AbstractTransactions.AddEmployeeTransaction;
import casestudy.PayrollDomain.PaymentClassification;
import casestudy.PayrollDomain.PaymentSchedule;
import casestudy.PayrollFactory.PayrollFactory;

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
