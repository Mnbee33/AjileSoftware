package casestudy.Payment;

import casestudy.PayrollDatabase.PayrollDatabase;
import casestudy.PayrollDomain.Employee;
import casestudy.PayrollDomain.Transaction;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaydayTransaction implements Transaction {
    private LocalDate itsPayDate;
    private Map<Integer, PayCheck> itsPayChecks = new HashMap<>();

    public PaydayTransaction(LocalDate payDate) {
        itsPayDate = payDate;
    }

    public void execute() {
        List<Integer> empIds = PayrollDatabase.getAllEmployeeIds();
        for (int empId : empIds) {
            Employee e = PayrollDatabase.getEmployee(empId);
            if (e.isPayDate(itsPayDate)) {
                PayCheck pc = new PayCheck(e.getPayPeriodStartDate(itsPayDate), itsPayDate);
                itsPayChecks.put(empId, pc);
                e.payday(pc);
            }
        }
    }

    public PayCheck getPayCheck(int empId) {
        return itsPayChecks.get(empId);
    }
}
