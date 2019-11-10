package casestudy.PayrollDatabase;

import casestudy.PayrollDomain.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PayrollDatabase {
    private static Map<Integer, Employee> itsEmployees = new HashMap<>();
    private static Map<Integer, Employee> itsUnionMembers = new HashMap<>();

    public static void addEmployee(int empId, Employee e) {
        itsEmployees.put(empId, e);
    }

    public static Employee getEmployee(int empId) {
        return itsEmployees.get(empId);
    }

    public static void clear() {
        itsEmployees.clear();
    }

    public static void deleteEmployee(int empId) {
        itsEmployees.remove(empId);
    }

    public static void addUnionMember(int memberId, Employee e) {
        itsUnionMembers.put(memberId, e);
    }

    public static Employee getUnionMember(int memberId) {
        return itsUnionMembers.get(memberId);
    }

    public static void removeUnionMember(int memberId) {
        itsUnionMembers.remove(memberId);
    }

    public static List<Integer> getAllEmployeeIds() {
        return new ArrayList<>(itsEmployees.keySet());
    }
}
