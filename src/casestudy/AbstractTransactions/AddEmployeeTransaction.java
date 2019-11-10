package casestudy.AbstractTransactions;

import casestudy.PayrollDatabase.GlobalDatabase;
import casestudy.PayrollDomain.Employee;
import casestudy.PayrollDomain.PaymentClassification;
import casestudy.PayrollDomain.PaymentMethod;
import casestudy.PayrollDomain.PaymentSchedule;
import casestudy.PayrollFactory.PayrollFactory;
import casestudy.PayrollFactoryImplementation.HoldMethod;
import casestudy.TransactionApplication.Transaction;

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
