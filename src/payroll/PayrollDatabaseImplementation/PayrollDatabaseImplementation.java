package payroll.PayrollDatabaseImplementation;

import payroll.PayrollDatabase.PayrollDatabase;
import payroll.PayrollDomain.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PayrollDatabaseImplementation implements PayrollDatabase {
    private static Map<Integer, Employee> itsEmployees = new HashMap<>();
    private static Map<Integer, Employee> itsUnionMembers = new HashMap<>();

    public void addEmployee(int empId, Employee e) {
        itsEmployees.put(empId, e);
    }

    public Employee getEmployee(int empId) {
        return itsEmployees.get(empId);
    }

    public void clear() {
        itsEmployees.clear();
    }

    public void deleteEmployee(int empId) {
        itsEmployees.remove(empId);
    }

    public void addUnionMember(int memberId, Employee e) {
        itsUnionMembers.put(memberId, e);
    }

    public Employee getUnionMember(int memberId) {
        return itsUnionMembers.get(memberId);
    }

    public void removeUnionMember(int memberId) {
        itsUnionMembers.remove(memberId);
    }

    public List<Integer> getAllEmployeeIds() {
        return new ArrayList<>(itsEmployees.keySet());
    }
}
