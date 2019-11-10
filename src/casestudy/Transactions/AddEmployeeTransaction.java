package casestudy.Transactions;

import casestudy.Classifications.PaymentClassification;
import casestudy.Methods.HoldMethod;
import casestudy.Methods.PaymentMethod;
import casestudy.PayrollDatabase.Employee;
import casestudy.PayrollDatabase.PayrollDatabase;
import casestudy.Schedules.PaymentSchedule;

public abstract class AddEmployeeTransaction implements Transaction {
    private int itsEmpId;
    private String itsName;
    private String itsAddress;

    public AddEmployeeTransaction(int empId, String name, String address) {
        itsEmpId = empId;
        itsName = name;
        itsAddress = address;
    }

    public void execute() {
        PaymentClassification pc = getClassification();
        PaymentSchedule ps = getSchedule();
        PaymentMethod pm = new HoldMethod();

        Employee e = new Employee(itsEmpId, itsName, itsAddress);
        e.setClassification(pc);
        e.setSchedule(ps);
        e.setMethod(pm);

        PayrollDatabase.addEmployee(itsEmpId, e);
    }

    protected abstract PaymentClassification getClassification();

    protected abstract PaymentSchedule getSchedule();
}