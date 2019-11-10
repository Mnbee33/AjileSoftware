package casestudy.PayrollDatabase;

import casestudy.PayrollDomain.Employee;

import java.util.List;

public interface PayrollDatabase {
    void addEmployee(int empId, Employee e);

    Employee getEmployee(int empId);

    void clear();

    void deleteEmployee(int empId);

    void addUnionMember(int memberId, Employee e);

    Employee getUnionMember(int memberId);

    void removeUnionMember(int memberId);

    List<Integer> getAllEmployeeIds();
}
