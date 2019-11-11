package payroll.TransactionFactoryImplementation;

import payroll.PayrollDatabase.GlobalDatabase;
import payroll.PayrollDomain.Employee;
import payroll.PayrollDomain.PayCheck;
import payroll.PayrollFactory.PayrollFactory;
import payroll.TransactionApplication.Transaction;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaydayTransaction implements Transaction {
    private LocalDate itsPayDate;
    private Map<Integer, PayCheck> itsPayChecks = new HashMap<>();
    private PayrollFactory itsPayrollFactory;

    public PaydayTransaction(LocalDate payDate, PayrollFactory pf) {
        itsPayDate = payDate;
        itsPayrollFactory = pf;
    }

    public void execute() {
        List<Integer> empIds = GlobalDatabase.database.getAllEmployeeIds();
        for (int empId : empIds) {
            Employee e = GlobalDatabase.database.getEmployee(empId);
            if (e.isPayDate(itsPayDate)) {
                PayCheck pc = itsPayrollFactory.makePaycheck(e.getPayPeriodStartDate(itsPayDate), itsPayDate);
                itsPayChecks.put(empId, pc);
                e.payday(pc);
            }
        }
    }

    public PayCheck getPayCheck(int empId) {
        return itsPayChecks.get(empId);
    }
}
