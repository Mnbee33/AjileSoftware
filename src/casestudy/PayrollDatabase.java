package casestudy;

import java.util.HashMap;
import java.util.Map;

public class PayrollDatabase {
    private static Map<Integer, Employee> itsEmployees = new HashMap<>();

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
}
