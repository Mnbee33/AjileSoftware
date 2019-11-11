package payroll.TransactionFactoryImplementation;

import payroll.AbstractTransactions.AddEmployeeTransaction;
import payroll.PayrollDomain.PaymentClassification;
import payroll.PayrollDomain.PaymentSchedule;
import payroll.PayrollFactory.PayrollFactory;

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
