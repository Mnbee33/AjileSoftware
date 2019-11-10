package casestudy.TransactionFactoryImplementation;

import casestudy.AbstractTransactions.AddEmployeeTransaction;
import casestudy.PayrollDomain.PaymentClassification;
import casestudy.PayrollDomain.PaymentSchedule;
import casestudy.PayrollFactory.PayrollFactory;

public class AddSalariedEmployee extends AddEmployeeTransaction {
    private double itsSalary;

    public AddSalariedEmployee(int empId, String name, String address, double salary, PayrollFactory pf) {
        super(empId, name, address, pf);
        itsSalary = salary;
    }

    @Override
    protected PaymentClassification getClassification() {
        return itsPayrollFactory.makeSalariedClassification(itsSalary);
    }

    @Override
    protected PaymentSchedule getSchedule() {
        return itsPayrollFactory.makeMonthlySchedule();
    }
}
