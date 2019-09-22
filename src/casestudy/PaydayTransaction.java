package casestudy;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaydayTransaction implements Transaction {
    private Calendar itsPayDate;
    private Map<Integer, PayCheck> itsPayChecks = new HashMap<>();

    public PaydayTransaction(Calendar payDate) {
        itsPayDate = payDate;
    }

    public void execute() {
        List<Integer> empIds = PayrollDatabase.getAllEmployeeIds();
        for (int empId : empIds) {
            Employee e = PayrollDatabase.getEmployee(empId);
            if (e.isPayDate()) {
                PayCheck pc = new PayCheck(e.getPayPeriodStartDate(itsPayDate), itsPayDate);
                itsPayChecks.put(empId, pc);
                e.payday(pc);
            }
        }
    }
}
