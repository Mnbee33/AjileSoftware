package payroll.AbstractTransactions;

import payroll.PayrollDatabase.GlobalDatabase;
import payroll.PayrollDomain.Employee;
import payroll.PayrollDomain.PaymentClassification;
import payroll.PayrollDomain.PaymentMethod;
import payroll.PayrollDomain.PaymentSchedule;
import payroll.PayrollFactory.PayrollFactory;
import payroll.PayrollFactoryImplementation.HoldMethod;
import payroll.TransactionApplication.Transaction;

public abstract class AddEmployeeTransaction implements Transaction {
    private int itsEmpId;
    private String itsName;
    private String itsAddress;
    protected PayrollFactory itsPayrollFactory;

    public AddEmployeeTransaction(int empId, String name, String address, PayrollFactory pf) {
        itsEmpId = empId;
        itsName = name;
        itsAddress = address;
        itsPayrollFactory = pf;
    }

    public void execute() {
        PaymentClassification pc = getClassification();
        PaymentSchedule ps = getSchedule();
        PaymentMethod pm = new HoldMethod();

        Employee e = new Employee(itsEmpId, itsName, itsAddress);
        e.setClassification(pc);
        e.setSchedule(ps);
        e.setMethod(pm);

        GlobalDatabase.database.addEmployee(itsEmpId, e);
    }

    protected abstract PaymentClassification getClassification();

    protected abstract PaymentSchedule getSchedule();
}
